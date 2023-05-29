package com.example.application.views.login;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.backend.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.PasswordField;

import jakarta.security.auth.message.AuthException;

@Route("login")
@PageTitle("Login | Weather Reports")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

	private final LoginForm login = new LoginForm();

//	public LoginView(UserService userService) {
//		//setId("login-view");
//		addClassName("login-view");
//		setSizeFull();
//		setAlignItems(Alignment.CENTER);
//		setJustifyContentMode(JustifyContentMode.CENTER);
//
//		var username = new TextField("Enter Username");
//
//		var password = new PasswordField("Enter Password");
//
//		add(
//
//				new H1("Welcome"),
//
//				username,
//
//				password,
//
//				new Button("Login", event -> {
//
//					try {
//
//						userService.authenticate(username.getValue(), password.getValue());
//
//						UI.getCurrent().navigate("weather");
//						// UI.getCurrent().navigate("chart");
//
//					} catch (AuthException e) {
//
//						Notification.show("Wrong credentials.");
//
//					}
//
//				})
//
//		);
//
//	}

	public LoginView() {
		addClassName("login-view");
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);
		login.setAction("login");
		add(new H1("Weather Reports"), login);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		// inform the user about an authentication error
		if (beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")) {
			login.setError(true);
		}
	}
}
