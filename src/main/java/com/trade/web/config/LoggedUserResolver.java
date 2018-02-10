package com.trade.web.config;

import com.trade.security.auth.token.AuthToken;
import com.trade.security.auth.token.AuthTokenManager;
import com.trade.web.user.LoggedUser;
import lombok.extern.log4j.Log4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Log4j
public class LoggedUserResolver implements HandlerMethodArgumentResolver {

	private final AuthTokenManager tokenManager;

	LoggedUserResolver(AuthTokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(LoggedUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
	                              ModelAndViewContainer mavContainer,
	                              NativeWebRequest webRequest,
	                              WebDataBinderFactory binderFactory) {
		String token = webRequest.getHeader("Authentication");
		if (token == null) {
			log.warn("User can't be resolved. Token not found.");
			return null;
		}

		AuthToken authToken = tokenManager.get(token);
		if (authToken == null) {
			log.warn("User can't be resolved. User by token not found.");
			return null;
		}

		return new LoggedUser(authToken.getUserId());
	}
}
