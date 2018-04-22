package com.trade.exception;

import com.trade.exception.client.ApiException;
import com.trade.exception.response.ApiExceptionResponse;
import com.trade.exception.response.ApiExceptionResponseCreator;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@ControllerAdvice
public class ExceptionsHandler {

	private final MessageSource messageSource;

	public ExceptionsHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(value = {ApiException.class})
	public ResponseEntity<ApiExceptionResponse> handleApiException(ApiException ex, WebRequest request) {
		ApiExceptionResponseCreator responseCreator = new ApiExceptionResponseCreator(messageSource, ex, request.getLocale());

		return ResponseEntity.status(ex.getReason().httpStatus())
		                     .contentType(APPLICATION_JSON)
		                     .body(responseCreator.create());
	}
}
