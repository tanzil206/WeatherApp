package com.example.application.views.login;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class LogoutView extends AppLayout {
//	private final SecurityConfig securityConfig;
//
//	public LogoutView(SecurityConfig securityConfig) {
//		this.securityConfig = securityConfig;
//		createHeader();
//		// createDrawer();
//	}
//
//	private void createHeader() {
//		H1 logo = new H1("Weather App");
//		logo.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.MEDIUM);
//
//		String u = securityConfig.getAuthenticatedUser().getAllUser().get(0).getName();
//		Button logout = new Button("Log out " + u, e -> securityConfig.logout());
//
//		var header = new HorizontalLayout(new DrawerToggle(), logo, logout);
//
//		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
//		header.expand(logo);
//		header.setWidthFull();
//		header.addClassNames(LumoUtility.Padding.Vertical.NONE, LumoUtility.Padding.Horizontal.MEDIUM);
//
//		addToNavbar(header);
//
//	}

//    private void createDrawer() {
//        addToDrawer(new VerticalLayout(
//                new RouterLink("List", ListView.class),
//                new RouterLink("Dashboard", DashboardView.class)
//        ));
//    }

}