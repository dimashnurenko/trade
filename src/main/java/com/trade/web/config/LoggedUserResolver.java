package com.trade.web.config;

import com.trade.security.token.AccessToken;
import com.trade.security.token.AccessTokenManager;
import com.trade.web.user.LoggedUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.trade.security.token.AccessTokenFilter.AUTHORIZATION_HEADER;

public class LoggedUserResolver implements HandlerMethodArgumentResolver {

	private final AccessTokenManager tokenManager;

	public LoggedUserResolver(AccessTokenManager tokenManager) {
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
	                              WebDataBinderFactory binderFactory) throws Exception {
		String token = webRequest.getHeader(AUTHORIZATION_HEADER);
		if (token == null) {
			return null;
		}

		AccessToken accessToken = tokenManager.get(token);
		if (accessToken == null) {
			return null;
		}

		return new LoggedUser(accessToken.getUserId());
	}
}
