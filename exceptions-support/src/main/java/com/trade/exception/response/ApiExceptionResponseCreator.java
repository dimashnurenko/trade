package com.trade.exception.response;

import com.trade.exception.client.ExceptionReason;
import com.trade.exception.client.ApiException;
import com.trade.exception.client.ApiExceptionDetails;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static java.util.stream.Collectors.toList;

public class ApiExceptionResponseCreator {

	private final MessageSource messageSource;
	private final ApiException exception;
	private final Locale locale;

	public ApiExceptionResponseCreator(MessageSource messageSource, ApiException exception, Locale locale) {
		this.messageSource = messageSource;
		this.exception = exception;
		this.locale = locale;
	}

	public ApiExceptionResponse create() {
		ExceptionReason reason = exception.getReason();

		return new ApiExceptionResponse(reason.errorCode(),
		                                getFormattedMessage(String.valueOf(reason.errorCode()), exception.getArgs()),
		                                exception.getDetails().stream().map(this::responseDetails).collect(toList()));

	}

	private String getFormattedMessage(String key, Object[] args) {
		try {
			return messageSource.getMessage(key, args, locale);
		} catch (NoSuchMessageException e) {
			if (Locale.ENGLISH.equals(locale)) {
				throw e;
			}
			return messageSource.getMessage(key, args, Locale.ENGLISH);
		}
	}

	private ApiExceptionResponseDetails responseDetails(ApiExceptionDetails details) {
		String key = details.getKey();
		return new ApiExceptionResponseDetails(key, getFormattedMessage(key, details.getArgs()));
	}
}
