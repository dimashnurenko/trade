package com.trade.exception;

public class ValidationException extends RuntimeException {
	private final int code;
	private final String reason;

	public ValidationException(int code, String reason) {
		this.code = code;
		this.reason = reason;
	}

	public int getCode() {
		return code;
	}

	public String getReason() {
		return reason;
	}
}
