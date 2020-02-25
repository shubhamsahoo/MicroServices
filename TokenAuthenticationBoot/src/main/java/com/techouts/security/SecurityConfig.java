package com.techouts.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*@Autowired
	private DataSource ds;*/

	/*	@Bean
		@Override
		public UserDetailsService userDetailsServiceBean() throws Exception {
	
			// add users in List
			List<UserDetails> users = new ArrayList<UserDetails>();
	
			users.add(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER").build());
	
			return new InMemoryUserDetailsManager(users);
		}
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*		http.authorizeRequests().antMatchers("/user").hasRole("USER").and().formLogin().usernameParameter("username") // default is username
						.passwordParameter("password") // default is password
						.loginPage("/authentication/login") // default is /login with an HTTP get
						.failureUrl("/authentication/login?failed") // default is /login?error
						.loginProcessingUrl("/authentication/login/process"); // default is /login with an HTTP post
		*/
		/*http.authorizeRequests().antMatchers("/home.htm").access("permitAll").antMatchers("/running.htm")
				.access("hasAnyRole('ROLE_PASSENGER','ROLE_ADMIN')").and().formLogin().and().logout()
				.logoutSuccessUrl("/home.htm").and().exceptionHandling().accessDeniedPage("/access_denied.jsp").and()
				.rememberMe().and().sessionManagement().invalidSessionUrl("/timeout.jsp").maximumSessions(2)
				.expiredUrl("/timeout.jsp");*/
		http.authorizeRequests().antMatchers("/user").access("hasAnyRole('USER')").and().formLogin()
				.and().logout().logoutSuccessUrl("/logoutuser").invalidateHttpSession(false)
				.and().exceptionHandling().accessDeniedPage("/access_denied.jsp")
				.and().rememberMe()
				.and().sessionManagement().invalidSessionUrl("/timeout.jsp").maximumSessions(2).expiredUrl("/timeout.jsp");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("USER");
		/*auth.jdbcAuthentication().dataSource(ds)
				.usersByUsernameQuery("SELECT UNAME,PWD,STATUS FROM USERS WHERE UNAME=?")
				.authoritiesByUsernameQuery("SELECT ROLEID,ROLE,UNAME FROM USER_ROLES WHERE UNAME=?");*/
	}

}