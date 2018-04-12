package com.trade.exception;

import com.trade.exception.client.ExceptionReason;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public enum CoreExceptionReason implements ExceptionReason {
	INCOMPATIBLE_DATA(1200, BAD_REQUEST);

	private final int errorCode;
	private final HttpStatus status;

	CoreExceptionReason(int errorCode, HttpStatus status) {
		this.errorCode = errorCode;
		this.status = status;
	}

	public int errorCode() {
		return errorCode;
	}

	public HttpStatus httpStatus() {
		return status;
	}
}
