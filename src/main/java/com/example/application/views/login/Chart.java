package com.example.application.views.login;

import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;

@Tag("Chart")
public class Chart extends Component {

	ChartJs chartjs;

	public Chart(ChartJs chartjs) {

		super();
		this.chartjs = chartjs;
		// return chart;
	}

}