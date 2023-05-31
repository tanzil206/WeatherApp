package com.example.application.backend.utility;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.application.backend.model.Hour;
import com.example.application.backend.service.HourService;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class HourDataProvider extends AbstractBackEndDataProvider<Hour, SearchFilter> {

	String date;
	
	String cityName;

	@Autowired
	HourService hourService;

	@Autowired
	public HourDataProvider(HourService hourService) {
		this.hourService = hourService;
	}

	@Override
	protected Stream<Hour> fetchFromBackEnd(Query<Hour, SearchFilter> query) {

		final List<Hour> DATABASE = new ArrayList<>(hourService.getHourlyForecast(cityName,date));

		// A real app should use a real database or a service
		// to fetch, filter and sort data.
		Stream<Hour> stream = DATABASE.stream();

		// Filtering
		if (query.getFilter().isPresent()) {
			stream = stream.filter(Hour -> query.getFilter().get().checkHour(Hour));
		}

		// Sorting
		if (query.getSortOrders().size() > 0) {
			stream = stream.sorted(sortComparator(query.getSortOrders()));
		}

		// Pagination
		return stream.skip(query.getOffset()).limit(query.getLimit());
	}

	@Override
	protected int sizeInBackEnd(Query<Hour, SearchFilter> query) {
		return (int) fetchFromBackEnd(query).count();
	}

	private static Comparator<Hour> sortComparator(List<QuerySortOrder> sortOrders) {
		return sortOrders.stream().map(sortOrder -> {
			Comparator<Hour> comparator = HourFieldComparator(sortOrder.getSorted());

			if (sortOrder.getDirection() == SortDirection.DESCENDING) {
				comparator = comparator.reversed();
			}

			return comparator;
		}).reduce(Comparator::thenComparing).orElse((p1, p2) -> 0);
	}

	private static Comparator<Hour> HourFieldComparator(String sorted) {
		Hour hour = new Hour();

		if (sorted.equals("date")) {
			return Comparator.comparing(Hour -> hour.getDate());
		}
		return (p1, p2) -> 0;
	}

	
	public String getCityName() {
		return cityName;
	}

	public void setCitName(String cityName) {
		this.cityName = cityName;
	}

	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
