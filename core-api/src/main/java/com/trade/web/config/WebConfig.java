package com.trade.web.config;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	private final LoggedUserResolver loggedUserResolver;

	@Autowired
	public WebConfig(LoggedUserResolver loggedUserResolver) {
		this.loggedUserResolver = loggedUserResolver;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(loggedUserResolver);
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:i18n/common_exceptions");
		messageSource.setDefaultEncoding(UTF_8.displayName());
		return messageSource;
	}

	@Bean
	public EventBus eventBus() {
		return new EventBus();
	}
}
