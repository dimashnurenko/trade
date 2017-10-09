package com.trade.web.security.token;

import com.trade.common.exception.InvalidTokenException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessTokenFilter extends BasicAuthenticationFilter {
	private final AccessTokenManager tokenManager;

	public AccessTokenFilter(AccessTokenManager tokenManager, AuthenticationManager authenticationManager) {
		super(authenticationManager);
		this.tokenManager = tokenManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException,
	                                                                                                                    ServletException {
		//TODO need to map this filter via spring security configuration
		String uri = request.getRequestURI();
		if (!uri.startsWith("/api")) {
			chain.doFilter(request, response);
			return;
		}
		String token = request.getHeader("Authorization");
		String userIdString = request.getHeader("UserId");
		if (token == null || userIdString == null) {
			throw new InvalidTokenException("The token and userId are required");
		}

		Long userId = Long.valueOf(userIdString);
		AccessToken authToken = tokenManager.get(userId);
		if (authToken == null) {
			throw new InvalidTokenException("The token is invalid.");
		}

		if (!authToken.getUserId().equals(userId)) {
			throw new InvalidTokenException("The token doesn't belong to user");
		}

		chain.doFilter(request, response);
	}
}
