package com.trade.web;

import com.trade.exception.ServerException;
import com.trade.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class ValidationExceptionHandler {

	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity handleValidationFail(ValidationException ex) {
		Map<String, String> map = new HashMap<>();
		map.put("code", String.valueOf(ex.getCode()));
		map.put("reason", ex.getReason());

		return status(BAD_REQUEST).body(map);
	}

	@ExceptionHandler(value = ServerException.class)
	public ResponseEntity handleServerErrors(ServerException ex) {
		Map<String, String> map = new HashMap<>();
		map.put("reason", ex.getMessage());

		return status(INTERNAL_SERVER_ERROR).body(map);
	}
}
