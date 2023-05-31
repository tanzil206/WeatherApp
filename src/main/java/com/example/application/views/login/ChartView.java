package com.example.application.views.login;

import java.util.List;
import java.util.ArrayList;
import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.vaadin.ui.Component;


import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.ui.AbstractJavaScriptComponent;
import jakarta.annotation.security.PermitAll;

import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.example.application.backend.utility.SearchFilter;
import com.example.application.views.grid.DailyWeather;
import com.example.application.views.grid.PaginatedGrid;
import com.example.application.views.main.MainView;
import com.example.application.backend.utility.LocationDataProvider;
import com.example.application.views.login.Chart;
import com.vaadin.ui.VerticalLayout;

@PermitAll
@Route(value = "chart", layout = MainView.class)
public class ChartView extends VerticalLayout {

	Chart chart;

	public ChartView() {

		// VerticalLayout layout = new VerticalLayout();
		// service.locationValueParse();
		//add(new H1("Chart View"));
		var tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		// add(new Chart());

		addComponent(chartData());

		//add(tabSheet);
	}

	public Component chartData() {
//		BarChartConfig config = new BarChartConfig();
//		config.data().labels("January", "February", "March", "April", "May", "June", "July")
//				.addDataset(new BarDataset().type().label("Dataset 1").backgroundColor("rgba(151,187,205,0.5)")
//						.borderColor("white").borderWidth(2))
//				.addDataset(new LineDataset().type().label("Dataset 2").backgroundColor("rgba(151,187,205,0.5)")
//						.borderColor("white").borderWidth(2))
//				.addDataset(new BarDataset().type().label("Dataset 3").backgroundColor("rgba(220,220,220,0.5)")).and();
//
//		config.options().responsive(true).title().display(true).position(Position.LEFT)
//				.text("Chart.js Combo Bar Line Chart").and().done();
//
//		List<String> labels = config.data().getLabels();
//		for (Dataset<?, ?> ds : config.data().getDatasets()) {
//			List<Double> data = new ArrayList<>();
//			for (int i = 0; i < labels.size(); i++) {
//				data.add((double) (Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100));
//			}
//
//			if (ds instanceof BarDataset) {
//				BarDataset bds = (BarDataset) ds;
//				bds.dataAsList(data);
//			}
//
//			if (ds instanceof LineDataset) {
//				LineDataset lds = (LineDataset) ds;
//				lds.dataAsList(data);
//			}
//		}
//
//		ChartJs chartjs = new ChartJs(config);
//
//		//chart = new Chart(chartjs);
//		// chart.setJ(true);
		// chart.setJsLoggingEnabled(true);

		BarChartConfig config = new BarChartConfig();
		config.data().labels("January", "February", "March", "April", "May", "June", "July")
				.addDataset(new BarDataset().type().label("Dataset 1").backgroundColor("rgba(151,187,205,0.5)")
						.borderColor("white").borderWidth(2))
				.addDataset(new LineDataset().type().label("Dataset 2").backgroundColor("rgba(151,187,205,0.5)")
						.borderColor("white").borderWidth(2))
				.addDataset(new BarDataset().type().label("Dataset 3").backgroundColor("rgba(220,220,220,0.5)")).and();

		config.options().responsive(true).title().display(true).position(Position.LEFT)
				.text("Chart.js Combo Bar Line Chart").and().done();

		List<String> labels = config.data().getLabels();
		for (Dataset<?, ?> ds : config.data().getDatasets()) {
			List<Double> data = new ArrayList<>();
			for (int i = 0; i < labels.size(); i++) {
				data.add((double) (Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100));
			}

			if (ds instanceof BarDataset) {
				BarDataset bds = (BarDataset) ds;
				bds.dataAsList(data);
			}

			if (ds instanceof LineDataset) {
				LineDataset lds = (LineDataset) ds;
				lds.dataAsList(data);
			}
		}

		ChartJs chart = new ChartJs(config);
		chart.setJsLoggingEnabled(true);

//		VerticalLayout layout = new VerticalLayout();
//		layout.addComponent(chart);
//		layout.setPadding(false);
//		add(layout);

		return chart;
	}
}