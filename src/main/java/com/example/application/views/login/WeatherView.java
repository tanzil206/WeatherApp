package com.example.application.views.login;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.example.application.views.grid.DailyWeather;
import com.example.application.backend.model.Hour;
import com.example.application.backend.model.Location;
import com.example.application.backend.repository.LocationRepository;
import com.example.application.backend.service.HourService;
import com.example.application.backend.service.LocationService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.example.application.views.grid.PaginatedGrid;
import jakarta.annotation.security.PermitAll;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.dialog.DialogVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.component.button.ButtonVariant;
import com.example.application.backend.utility.SearchFilter;
import com.example.application.backend.utility.LocationDataProvider;
import com.example.application.backend.utility.HourDataProvider;

@PermitAll
@Route(value = "", layout = MainView.class)
public class WeatherView extends VerticalLayout {

	@Autowired
	LocationService service;

	@Autowired
	HourService hourService;

	private int totalAmountOfPages;
	private int itemsPerPage = 2;
	private int currentPageNumber = 1;

	public WeatherView(LocationService service, HourService hourService) {

		this.service = service;
		this.hourService = hourService;
		service.jsonValueParse();
		add(new H1("Daily Weather Reports"));
		var tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		tabSheet.add("", GridDataProvider(hourService));

		add(tabSheet);
	}

	public Component GridDataProvider(HourService hourService) {

		SearchFilter searchFilter = new SearchFilter();

		LocationDataProvider dataProvider = new LocationDataProvider(service);

		ConfigurableFilterDataProvider<Location, Void, SearchFilter> filterDataProvider = dataProvider
				.withConfigurableFilter();

		//Grid<Location> grid = new Grid<>(Location.class, false);
		
		PaginatedGrid<Location> grid = new PaginatedGrid<>();

		// totalAmountOfPages = RestApi.getPageCount(itemsPerPage);
		// List<Location> initialItems = RestApi.getPageItems(currentPageNumber,
		// totalAmountOfPages, itemsPerPage);
		// grid.setItems(initialItems);
//		Button nextButton = new Button("Next page", e -> {
//			if (currentPageNumber >= totalAmountOfPages) {
//				return;
//			}
//			//List<Location> nextPageItems = RestApi.getPageItems(++currentPageNumber, totalAmountOfPages, itemsPerPage);
//			//grid.setItems(nextPageItems);
//		});
//		Button previousButton = new Button("Previous page", e -> {
//			if (currentPageNumber <= 1) {
//				return;
//			}
//			//List<Location> prevPageItems = RestApi.getPageItems(--currentPageNumber, totalAmountOfPages, itemsPerPage);
//			//grid.setItems(prevPageItems);
//		});

		grid.setSelectionMode(Grid.SelectionMode.SINGLE);

		grid.addColumn(Location::getCity_name).setHeader("City Name");
		grid.addColumn(Location::getPer_temperature).setHeader("Temperature").setSortable(true);
		grid.addColumn(Location::getWind_speed).setHeader("Wind Speed").setSortable(true);
		grid.addColumn(Location::getDate).setHeader("Date").setSortable(true);
		grid.addColumn(new ComponentRenderer<>(Location -> {

			// button for saving the name to backend
			Button update = new Button("Favourite", event -> {
				String city_name = Location.getCity_name();
				service.updatefavourite(city_name);
				grid.getDataProvider().refreshItem(Location);
			});
			// layouts for placing the text field on top
			// of the buttons
			HorizontalLayout buttons = new HorizontalLayout(update);
			return new VerticalLayout(buttons);
		})).setHeader("Actions");

		grid.setPageSize(1);
	//	grid.setP
		 grid.setPaginatorSize(5);
		grid.getDataProvider().refreshAll();
		grid.setItems(filterDataProvider);

		// Sets the max number of items to be rendered on the grid for each page

		// Sets how many pages should be visible on the pagination before and/or after
		// the current selected page
		//

		TextField searchField = new TextField();
		searchField.setWidth("50%");
		searchField.setPlaceholder("Search");
		searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
		searchField.setValueChangeMode(ValueChangeMode.EAGER);
		searchField.addValueChangeListener(e -> {
			searchFilter.setSearchTerm(e.getValue());
			filterDataProvider.setFilter(searchFilter);
		});

		VerticalLayout layout = new VerticalLayout(searchField, grid);
		layout.setPadding(false);
		add(layout);

		grid.addSelectionListener(selection -> {
			Optional<Location> optionalPerson = selection.getFirstSelectedItem();
			String cityName = optionalPerson.get().getCity_name();
			String date = optionalPerson.get().getDate();
			// if (optionalPerson.isPresent()) {

			DailyWeather dailyWeather = new DailyWeather();
			Dialog dialog = dailyWeather.dailyForecast(hourService,cityName, date);

			add(dialog);
			dialog.open();

			// }
		});

		return layout;
	}

}