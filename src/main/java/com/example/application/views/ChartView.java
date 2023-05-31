package com.example.application.views;

import java.util.ArrayList;
import java.util.List;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.dependency.JsModule;
import com.example.application.backend.utility.Chart;

@PermitAll
@Route(value = "chart", layout = MainView.class)
public class ChartView extends VerticalLayout {

	public ChartView() {

		var tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		tabSheet.add("", getChart());
		add(tabSheet);
	}

	public Component getChart() {
		
		Chart chart = new Chart();
//		Chart chart = new Chart(ChartType.COLUMN);
//
//		Configuration conf = chart.getConfiguration();
//		conf.setTitle("Daily Weather Forecast");
//
//		ListSeries series = new ListSeries("Diameter");
//		series.setData(4900, 12100, 12800, 6800, 143000, 125000, 51100, 49500);
//		conf.addSeries(series);
//
//		XAxis xaxis = new XAxis();
//		xaxis.setCategories("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune");
//		xaxis.setTitle("Planet");
//		conf.addxAxis(xaxis);
//
//		// Set the Y axis title
//		YAxis yaxis = new YAxis();
//		yaxis.setTitle("Diameter");
//		yaxis.getLabels().setFormatter("function() {return Math.floor(this.value/1000) + \'Mm\';}");
//		yaxis.getLabels().setStep(2);
//		conf.addyAxis(yaxis);
//
//		VerticalLayout layout = new VerticalLayout(chart);
//		layout.setPadding(false);
//		add(layout);
//
     	return chart;

	}

}