package com.example.application.views.grid;

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

public class DailyWeather {

	@Autowired
	HourService hourService;

	public Dialog dailyForecast(HourService hourService, String date) {
		
		this.hourService= hourService;
		Dialog dialog = new Dialog();
		dialog.setHeaderTitle("Daily Weather");

		HourDataProvider hourDataProvider = new HourDataProvider(hourService);

		hourDataProvider.setDate(date);

		ConfigurableFilterDataProvider<Hour, Void, SearchFilter> hourFilterDataProvider = hourDataProvider
				.withConfigurableFilter();
		// Create the content
		Grid<Hour> grid = new Grid<>(Hour.class, false);

		grid.addColumn(Hour::getDate).setHeader("Date");
//dailygGrid.addColumn(Hour::getHour).setHeader("Hour").setSortable(true);
		grid.addColumn(Hour::getTempLevel).setHeader("Temperature").setSortable(true);
		grid.addColumn(Hour::getHumidityLevel).setHeader("Humidity").setSortable(true);

		grid.setItems(hourFilterDataProvider);
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
