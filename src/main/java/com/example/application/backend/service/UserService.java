package com.example.application.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.backend.model.User;
import com.example.application.backend.repository.UserRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.VaadinSession;
import com.example.application.views.login.WeatherView;
import com.example.application.views.main.MainView;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import jakarta.security.auth.message.AuthException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	private static final String LOGOUT_SUCCESS_URL = "/";

	public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {

	}

	public ArrayList<User> getAllUser() {
		ArrayList<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public long adduser(User user) {
		return userRepository.save(user).getId();
	}

	public User getById(long id) {
		return userRepository.findById(id);
	}

	public User getByName(String name) {
		return userRepository.findByName(name);
	}

	public UserService(UserRepository userRepository) {

		this.userRepository = userRepository;

	}

	public void authenticate(String username, String password) throws AuthException {

		User user = userRepository.findByName(username);

		if (user != null && user.checkPassword(password)) {

			VaadinSession.getCurrent().setAttribute(User.class, user);

			// createRoutes(user.getRole());

		} else {

			throw new AuthException();

		}

	}

//	public List<AuthorizedRoute> getAuthorizedRoutes() {
//
//		var routes = new ArrayList<AuthorizedRoute>();
//
//		// if (role.equals(Role.USER)) {
//
//		routes.add(new AuthorizedRoute("", "Main", MainView.class));
//		routes.add(new AuthorizedRoute("weather", "weather", WeatherView.class));
//		// routes.add(new AuthorizedRoute("logout", "Logout", LogoutView.class));
//
//		return routes;
//	}
	
    public UserDetails getAuthenticatedUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Object principal = context.getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserDetails) context.getAuthentication().getPrincipal();
        }
        // Anonymous or no authentication.
        return null;
    }

	public void logout() {
		UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
	}
//
//	public static boolean isUserLoggedIn() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
//				&& authentication.isAuthenticated();
//	}
}
