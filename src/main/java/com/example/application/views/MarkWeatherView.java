package com.example.application.views;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.application.backend.model.Location;
import com.example.application.backend.service.HourService;
import com.example.application.backend.service.LocationService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.example.application.views.grid.PaginatedGrid;
import jakarta.annotation.security.PermitAll;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.renderer.ComponentRenderer;


@PermitAll
@Route(value = "/favourite", layout = MainView.class)
public class MarkWeatherView extends VerticalLayout {

	@Autowired
	LocationService service;

	@Autowired
	HourService hourService;

	public MarkWeatherView(LocationService service, HourService hourService) {

		this.service = service;
		this.hourService = hourService;
		service.jsonValueParse();
		add(new H1("Favourite Location Weather Reports"));
		var tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		tabSheet.add("", GridDataProvider(hourService));
		add(tabSheet);
	}

	public Component GridDataProvider(HourService hourService) {

		PaginatedGrid<Location> grid = new PaginatedGrid<>();
		grid.setSelectionMode(Grid.SelectionMode.SINGLE);
		grid.addColumn(Location::getCity_name).setHeader("City Name");
		grid.addColumn(Location::getPer_temperature).setHeader("Temperature").setSortable(true);
		grid.addColumn(Location::getWind_speed).setHeader("Wind Speed").setSortable(true);
		grid.addColumn(Location::getDate).setHeader("Date").setSortable(true);
		grid.addColumn(new ComponentRenderer<>(Location -> {

			// button for saving the name to backend
			Button update = new Button("Unfavourite", event -> {
				String city_name = Location.getCity_name();
				service.updatefavourite(city_name,"no");
				grid.getDataProvider().refreshItem(Location);
			});
			HorizontalLayout buttons = new HorizontalLayout(update);
			return new VerticalLayout(buttons);
		})).setHeader("Actions");

		grid.getDataProvider().refreshAll();
		GridListDataView<Location> dataView = grid.setItems(service.getMarkedDailyForecast());
		grid.setPageSize(2);
		grid.setPaginatorSize(2);

		TextField searchField = new TextField();
		searchField.setWidth("50%");
		searchField.setPlaceholder("Search");
		searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
		searchField.setValueChangeMode(ValueChangeMode.EAGER);
		searchField.addValueChangeListener(e -> {
			dataView.addFilter(location -> {
				String searchTerm = searchField.getValue().trim();

				if (searchTerm.isEmpty())
					return true;

				boolean matchesFullName = matchesTerm(location.getCity_name(), searchTerm);
				boolean matchesDate = matchesTerm(location.getDate(), searchTerm);
				return matchesFullName || matchesDate;
			});
		});

		VerticalLayout layout = new VerticalLayout(searchField, grid);
		layout.setPadding(false);
		add(layout);

		grid.addSelectionListener(selection -> {
			Optional<Location> optionalPerson = selection.getFirstSelectedItem();
			String cityName = optionalPerson.get().getCity_name();
			String date = optionalPerson.get().getDate();

			HourlyWeatherView hourlyWeather = new HourlyWeatherView();
			Dialog dialog = hourlyWeather.hourlyForecast(hourService, cityName, date);

			add(dialog);
			dialog.open();

		});

		return layout;
	}

	private boolean matchesTerm(String value, String searchTerm) {
		return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
	}

}