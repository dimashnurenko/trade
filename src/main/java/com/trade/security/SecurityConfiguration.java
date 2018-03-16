package com.trade.security;

import com.trade.security.auth.AuthenticationService;
import com.trade.security.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final AuthenticationEntryPoint notAuthorizedEntryPoint = (request, response, error) -> response.sendError(SC_UNAUTHORIZED, "Access Denied");

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/swagger-resources/**").permitAll()
		    .antMatchers("/swagger-resources").permitAll()
		    .antMatchers("/swagger-ui.html").permitAll()
		    .antMatchers("/api/v1/auth").permitAll()
		    .antMatchers("/api/v1/auth/token").permitAll()
		    .and()
		    .exceptionHandling().authenticationEntryPoint(notAuthorizedEntryPoint)
		    .and()
		    .csrf().disable();
	}

	@Bean
	public FilterRegistrationBean authFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new AuthFilter(authenticationService));
		registrationBean.setOrder(2);
		return registrationBean;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
