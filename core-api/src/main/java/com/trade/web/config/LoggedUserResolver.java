package com.trade.web.config;

import com.trade.auth.user.UserRepo;
import com.trade.auth.user.model.UserEntity;
import com.trade.exception.client.BadAuthException;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.trade.exception.client.ApiExceptionDetails.exceptionDetails;

@Component
public class LoggedUserResolver implements HandlerMethodArgumentResolver {

	private final UserRepo userRepo;

	public LoggedUserResolver(UserRepo userRepo) {
		this.userRepo = userRepo;
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.isAuthenticated()) {
			throw new BadAuthException(exceptionDetails("user.not.authorized"));
		}

		User principal = (User) authentication.getPrincipal();
		UserEntity user = userRepo.findFirstByPhone(principal.getUsername());
		if (user == null) {
			throw new BadAuthException(exceptionDetails("user.not.authorized"));
		}

		return new LoggedUser(user.getId());
	}
}
