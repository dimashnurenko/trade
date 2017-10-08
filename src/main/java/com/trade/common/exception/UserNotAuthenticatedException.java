package com.trade.common.exception;

public class UserNotAuthenticatedException extends RuntimeException {
	public UserNotAuthenticatedException(String message) {
		super(message);
	}
}
