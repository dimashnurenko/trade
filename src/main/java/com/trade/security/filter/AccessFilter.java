package com.trade.security.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter extends GenericFilterBean {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String accessToken = httpRequest.getHeader("AccessToken");
		if (httpRequest.getRequestURI().contains("/api/") && (accessToken == null || accessToken.isEmpty())) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendError(401, "Access denied.");
			return;
		}

		chain.doFilter(request, response);
	}
}
