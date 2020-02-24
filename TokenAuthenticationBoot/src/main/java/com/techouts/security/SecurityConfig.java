package com.techouts.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {

		// add users in List
		List<UserDetails> users = new ArrayList<UserDetails>();

		users.add(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER").build());

		return new InMemoryUserDetailsManager(users);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin();
		http.logout().logoutUrl("/logoutuser").logoutSuccessUrl("/index.jsp");
	}
}