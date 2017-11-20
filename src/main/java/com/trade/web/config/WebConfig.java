package com.trade.web.config;

import com.trade.security.token.AccessTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	private final AccessTokenManager tokenManager;

	@Autowired
	public WebConfig(AccessTokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new LoggedUserResolver(tokenManager));
	}
}
