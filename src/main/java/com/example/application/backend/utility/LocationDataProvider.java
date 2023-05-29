package com.example.application.backend.utility;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.application.backend.model.Location;
import com.example.application.backend.repository.LocationRepository;
import com.example.application.backend.service.HourService;
import com.example.application.backend.service.LocationService;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class LocationDataProvider extends AbstractBackEndDataProvider<Location, SearchFilter> {

	@Autowired
	LocationService locationService;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	public LocationDataProvider(LocationService locationService) {
		this.locationService = locationService;
	}


	@Override
	protected Stream<Location> fetchFromBackEnd(Query<Location, SearchFilter> query) {

		final List<Location> DATABASE = new ArrayList<>(locationService.getDailyForecast());

		// A real app should use a real database or a service
		// to fetch, filter and sort data.
		Stream<Location> stream = DATABASE.stream();

		// Filtering
		if (query.getFilter().isPresent()) {
			stream = stream.filter(Location -> query.getFilter().get().test(Location));
		}

		// Sorting
		if (query.getSortOrders().size() > 0) {
			stream = stream.sorted(sortComparator(query.getSortOrders()));
		}

		// Pagination
		return stream.skip(query.getOffset()).limit(query.getLimit());
	}

	@Override
	protected int sizeInBackEnd(Query<Location, SearchFilter> query) {
		return (int) fetchFromBackEnd(query).count();
	}

	private static Comparator<Location> sortComparator(List<QuerySortOrder> sortOrders) {
		return sortOrders.stream().map(sortOrder -> {
			Comparator<Location> comparator = LocationFieldComparator(sortOrder.getSorted());

			if (sortOrder.getDirection() == SortDirection.DESCENDING) {
				comparator = comparator.reversed();
			}

			return comparator;
		}).reduce(Comparator::thenComparing).orElse((p1, p2) -> 0);
	}

	private static Comparator<Location> LocationFieldComparator(String sorted) {
		Location location = new Location();

		if (sorted.equals("latitude")) {
			return Comparator.comparing(Location -> location.getLatitude());
		} else if (sorted.equals("date")) {
			return Comparator.comparing(Location -> location.getDate());
		}
		return (p1, p2) -> 0;
	}
}