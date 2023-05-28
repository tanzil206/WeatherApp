package com.example.application.views.login;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.application.backend.model.Location;
import com.example.application.backend.repository.LocationRepository;
import com.example.application.backend.service.LocationService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.component.button.ButtonVariant;
import com.example.application.backend.utility.SearchFilter;
import com.example.application.backend.utility.LocationDataProvider;


public class WeatherView extends VerticalLayout {

	@Autowired
	LocationService service;

	public WeatherView(LocationService service) {

		this.service = service;
		service.locationValueParse();
		add(new H1("Weather View"));
		var tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		// tabSheet.add("Grid backed with a list", listGrid());
		tabSheet.add("Weather View Board", GridDataProvider());

		add(tabSheet);
	}

//	    private Component listGrid() {
//	        var grid = new Grid<>(Person.class);
//	        grid.setColumns("firstName", "lastName", "email");
//	        grid.setItems(repo.findAll());
//	        return grid;
//	    }

	public Component GridDataProvider() {

		SearchFilter searchFilter = new SearchFilter();

		LocationDataProvider dataProvider = new LocationDataProvider(service);

		ConfigurableFilterDataProvider<Location, Void, SearchFilter> filterDataProvider = dataProvider
				.withConfigurableFilter();

		Grid<Location> grid = new Grid<>(Location.class, false);
		
		Location loc =new Location();
		grid.setSelectionMode(Grid.SelectionMode.SINGLE);

		grid.addColumn(Location::getLatitude).setHeader("City Name");
		grid.addColumn(Location::getPer_temperature).setHeader("Temperature").setSortable(true);
		grid.addColumn(Location::getWind_speed).setHeader("Wind Speed").setSortable(true);

		grid.addColumn(new ComponentRenderer<>(Location -> {

			

		    // button for saving the name to backend
		    Button update = new Button("Favourite", event -> {
		        loc.setFavourite("yes");
		        grid.getDataProvider().refreshItem(loc);
		    });

//		    // button that removes the item
//		    Button remove = new Button("Remove", event -> {
//		        ListDataProvider<Person> dataProvider =
//		            (ListDataProvider<Person>) grid
//		                .getDataProvider();
//		        dataProvider.getItems().remove(person);
//		        dataProvider.refreshAll();
//		    });

		    // layouts for placing the text field on top
		    // of the buttons
		    HorizontalLayout buttons =
		            new HorizontalLayout(update);
		    return new VerticalLayout(buttons);
		})).setHeader("Actions");
		
		// Sets the max number of items to be rendered on the grid for each page
		grid.setPageSize(10);

		grid.setItems(filterDataProvider);

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
			// if (optionalPerson.isPresent()) {
			System.out.println("Click");
			Dialog dialog = new Dialog();

			dialog.setHeaderTitle("Daily Weather");

//				VerticalLayout dialogLayout = createDialogLayout();
//				dialog.add(dialogLayout);

			// Create the content
			Grid<Location> dailygGrid = new Grid<>(Location.class, false);

			dailygGrid.addColumn(Location::getLatitude).setHeader("City Name");
			dailygGrid.addColumn(Location::getPer_temperature).setHeader("Temperature").setSortable(true);
			dailygGrid.addColumn(Location::getWind_speed).setHeader("Wind Speed").setSortable(true);

			dailygGrid.setItems(filterDataProvider);
			Button closeButton = new Button(new Icon("lumo", "cross"), (e) -> dialog.close());
			closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
			dialog.getHeader().add(closeButton);

			dialog.setModal(false);
			dialog.setDraggable(true);
			dialog.setResizable(true);

			dialog.add(dailygGrid);
			add(dialog);
			dialog.open();

			// }
		});

		return layout;
	}

	private boolean matchesTerm(String value, String searchTerm) {
		return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
	}
}