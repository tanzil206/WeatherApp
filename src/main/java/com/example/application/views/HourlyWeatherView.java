package com.example.application.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.backend.model.Hour;
import com.example.application.backend.service.HourService;
import com.example.application.backend.utility.HourDataProvider;
import com.example.application.backend.utility.SearchFilter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.dialog.DialogVariant;
import com.vaadin.flow.component.icon.Icon;
import com.example.application.views.grid.*;

public class HourlyWeatherView {

	@Autowired
	HourService hourService;

	public Dialog hourlyForecast(HourService hourService, String cityName, String date) {

		this.hourService = hourService;
		Dialog dialog = new Dialog();
		dialog.setHeaderTitle("Daily Weather");

		HourDataProvider hourDataProvider = new HourDataProvider(hourService);

		hourDataProvider.setCitName(cityName);

		hourDataProvider.setDate(date);

		ConfigurableFilterDataProvider<Hour, Void, SearchFilter> hourFilterDataProvider = hourDataProvider
				.withConfigurableFilter();
		// Create the content
		PaginatedGrid<Hour> grid = new PaginatedGrid<>();

		grid.addColumn(Hour::getDate).setHeader("Date");
		grid.addColumn(Hour::getTime).setHeader("Hour").setSortable(true);
		grid.addColumn(Hour::getTempLevel).setHeader("Temperature").setSortable(true);
		grid.addColumn(Hour::getHumidityLevel).setHeader("Humidity").setSortable(true);
		grid.addColumn(Hour::getRain).setHeader("Rain").setSortable(true);
		grid.addColumn(Hour::getWindSpeedLevel).setHeader("Wind Speed").setSortable(true);
		grid.getDataProvider().refreshAll();
		grid.setItems(hourService.getHourlyForecast(cityName, date));
		grid.setPageSize(2);
		grid.setPaginatorSize(2);
		Button closeButton = new Button(new Icon("lumo", "cross"), (e) -> dialog.close());
		closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		dialog.getHeader().add(closeButton);
		dialog.addThemeVariants(DialogVariant.LUMO_NO_PADDING);
		dialog.setSizeFull();
		dialog.setModal(true);
		dialog.setDraggable(true);
		dialog.setResizable(true);

		dialog.add(grid);

		return dialog;
	}

}
