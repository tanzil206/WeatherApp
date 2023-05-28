package com.example.application.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.backend.model.User;
import com.example.application.backend.repository.UserRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.VaadinSession;
import com.example.application.views.login.WeatherView;
import com.example.application.views.login.ChartView;

import jakarta.security.auth.message.AuthException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;


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
		    
		    public List<AuthorizedRoute> getAuthorizedRoutes() {
		    	
		    	        var routes = new ArrayList<AuthorizedRoute>();
		    	
		    	
		    	    //    if (role.equals(Role.USER)) {
		    	
		    	         //   routes.add(new AuthorizedRoute("weather", "Weather", WeatherView.class));
		    	            routes.add(new AuthorizedRoute("chart", "Chart", ChartView.class));
		    	            //routes.add(new AuthorizedRoute("logout", "Logout", LogoutView.class));
		    	            
		    	            return routes;
}}
