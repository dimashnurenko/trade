package com.trade.web.security.token;

import com.trade.common.exception.InvalidTokenException;
import com.trade.domain.user.User;
import com.trade.domain.user.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.trade.web.security.SecurityUtils.createSecurityToken;

public class AuthTokenFilter implements Filter {
	private final TokenRepo tokenRepo;
	private final UserRepo userRepo;

	public AuthTokenFilter(TokenRepo tokenRepo, UserRepo userRepo) {
		this.tokenRepo = tokenRepo;
		this.userRepo = userRepo;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String token = httpRequest.getHeader("Authorization");
		String userIdString = httpRequest.getHeader("UserId");
		if (token == null || userIdString == null) {
			filter.doFilter(request, response);
			return;
		}

		Long userId = Long.valueOf(userIdString);

		AuthToken authToken = tokenRepo.findFirstByUserId(userId);
		if (authToken == null || isExpired(authToken)) {
			throw new InvalidTokenException("The token has expired");
		}

		if (!authToken.getToken().equals(token)) {
			throw new InvalidTokenException("The token doesn't belong to user");
		}

		User user = userRepo.findOne(userId);
		SecurityContextHolder.getContext().setAuthentication(createSecurityToken(user));

		filter.doFilter(request, response);
	}

	private boolean isExpired(AuthToken authToken) {
		//todo  need to add logic which checks if the token expiration date is before current date
		return false;
	}

	@Override
	public void destroy() {

	}
}
