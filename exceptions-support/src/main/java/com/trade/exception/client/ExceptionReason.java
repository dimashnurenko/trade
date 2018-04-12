package com.trade.exception.client;

import org.springframework.http.HttpStatus;

public interface ExceptionReason {
	HttpStatus httpStatus();

	int errorCode();
}
