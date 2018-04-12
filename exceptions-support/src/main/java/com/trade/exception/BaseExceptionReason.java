package com.trade.exception;

import com.trade.exception.client.ExceptionReason;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public enum BaseExceptionReason implements ExceptionReason {

	RESOURCE_NOT_FOUND(1000, NOT_FOUND),
	BAD_AUTH(1001, UNAUTHORIZED),
	SERVER_ERROR(1002, INTERNAL_SERVER_ERROR);

	private int errorCode;
	private HttpStatus httpStatus;

	BaseExceptionReason(int errorCode, HttpStatus httpStatus) {
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	public int errorCode() {
		return errorCode;
	}

	public HttpStatus httpStatus() {
		return httpStatus;
	}
}
