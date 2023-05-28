package com.example.application.views.login;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import com.vaadin.flow.component.html.Div;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.ChartJs;
import com.example.application.backend.model.Location;
import com.example.application.backend.repository.LocationRepository;
import com.example.application.backend.service.LocationService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.example.application.backend.utility.SearchFilter;
import com.example.application.backend.utility.LocationDataProvider;
import com.s


@Route("chart")
public class ChartView extends HorizontalLayout {
	

	    public ChartView() {

	        // Creating a chart display area
	        SOChart soChart = new SOChart();
	        soChart.setSize("900px", "500px");

	        // Let us define some inline data
	        CategoryData labels =
	                new CategoryData("April Fool's Day", "Marriage Day", "Election Day", "Any Other Day");
	        Data data = new Data(5, 20, 100, 2);

	        // Axes
	        XAxis xAxis;
	        YAxis yAxis;

	        // Bar chart
	        BarChart bc1 = new BarChart(labels, data); // First bar chart
	        xAxis = new XAxis(labels);
	        xAxis.getLabel(true).setRotation(45);
	        yAxis = new YAxis(data);
	        RectangularCoordinate coordinate = new RectangularCoordinate(xAxis, yAxis);
	        bc1.plotOn(coordinate); // Bar chart needs to be plotted on a coordinate system
	        coordinate.getPosition(true).setRight(Size.percentage(60)); // Leave space on the right side

//	        BarChart bc2 = new BarChart(data, labels); // Second bar chart
//	        xAxis = new XAxis(data);
//	        yAxis = new YAxis(labels);
//	        coordinate = new RectangularCoordinate(xAxis, yAxis);
//	        bc2.plotOn(coordinate); // Bar chart needs to be plotted on a coordinate system
//	        coordinate.getPosition(true).setLeft(Size.percentage(60)); // Leave space on the left side

	        // Just to demonstrate it, we are creating a "Download" and a "Zoom" toolbox button
	        Toolbox toolbox = new Toolbox();
	        toolbox.addButton(new Toolbox.Download(), new Toolbox.Zoom());

	        // Switching off the default legend
	        soChart.disableDefaultLegend();

	        // Let's add some titles
	        Title title = new Title("Probability of Getting Fooled");
	        title.setSubtext("Truth is always simple but mostly hidden - Syam");

	        // Add the chart components to the chart display area
	        soChart.add(bc1, bc2, toolbox, title);

	        // Add to the view
	        add(soChart);
	    }}
	