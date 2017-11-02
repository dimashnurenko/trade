package com.trade.security;

import com.trade.security.token.AccessTokenFilter;
import com.trade.security.token.AccessTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final AuthenticationEntryPoint notAuthorizedEntryPoint = (request, response, error) -> response.sendError(SC_UNAUTHORIZED, "Access Denied");

	private final UserDetailsService userDetailsService;
	private final AccessTokenManager tokenManager;

	@Autowired
	public SecurityConfiguration(UserDetailsService userDetailsService,
	                             AccessTokenManager tokenManager) {
		this.userDetailsService = userDetailsService;
		this.tokenManager = tokenManager;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());

		return authenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/swagger-resources/**").permitAll()
		    .antMatchers("/swagger-resources").permitAll()
		    .antMatchers("/swagger-ui.html").permitAll()
		    .antMatchers("/login").permitAll()
		    .antMatchers("/signup").permitAll()
//		    .antMatchers("/api/**").authenticated()
		    .and()
		    .addFilterBefore(accessTokenFilter(), BasicAuthenticationFilter.class)
		    .exceptionHandling().authenticationEntryPoint(notAuthorizedEntryPoint)
		    .and()
		    .csrf().disable();
	}

	@Bean
	public AccessTokenFilter accessTokenFilter() throws Exception {
		return new AccessTokenFilter(tokenManager, authenticationManager());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
