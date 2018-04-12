package com.trade.exception.client;

public class ApiExceptionDetails {
	private final String key;
	private final Object[] args;

	private ApiExceptionDetails(String key, Object[] args) {
		this.key = key;
		this.args = args;
	}

	public static ApiExceptionDetails exceptionDetails(String key) {
		return new ApiExceptionDetails(key, new Object[]{});
	}

	public static ApiExceptionDetails exceptionDetails(String key, Object[] args) {
		return new ApiExceptionDetails(key, args);
	}

	public String getKey() {
		return key;
	}

	public Object[] getArgs() {
		return args;
	}
}
