package com.example.application.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import com.example.application.views.login.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/images/*.png").permitAll();
		super.configure(http);
		setLoginView(http, LoginView.class);
	}

	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder().username("tanzil")
				// password = password with this hash, don't tell anybody :-)
				.password(this.passwordEncoder().encode("1234")).roles("USER").build();
		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	@Primary
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}