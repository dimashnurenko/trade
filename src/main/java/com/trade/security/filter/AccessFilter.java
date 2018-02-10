package com.trade.security.filter;

import com.trade.security.exception.AccessException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccessFilter extends GenericFilterBean {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String accessToken = httpRequest.getHeader("AccessToken");
		if (httpRequest.getRequestURI().contains("/api/") && (accessToken == null || accessToken.isEmpty())) {
			throw new AccessException("Access token is absent or invalid");
		}

		chain.doFilter(request, response);
	}
}
