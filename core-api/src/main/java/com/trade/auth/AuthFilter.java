package com.trade.auth;

import com.trade.auth.web.UserInfoDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.trade.auth.SecurityUtils.createAuthentication;

@Component
public class AuthFilter extends GenericFilterBean {

	private final AuthenticationService authService;

	public AuthFilter(AuthenticationService authService) {
		this.authService = authService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String authentication = httpRequest.getHeader("Authentication");
		if (authentication == null || authentication.isEmpty()) {
			chain.doFilter(request, response);
			return;
		}

		UserInfoDto userInfo = authService.authenticate(authentication);
		SecurityContextHolder.getContext().setAuthentication(createAuthentication(userInfo));

		chain.doFilter(request, response);
	}
}
