package com.trade.exception.response;

public class ApiExceptionResponseDetails {
	private final String key;
	private final String message;

	public ApiExceptionResponseDetails(String key, String message) {
		this.key = key;
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public String getMessage() {
		return message;
	}
}
