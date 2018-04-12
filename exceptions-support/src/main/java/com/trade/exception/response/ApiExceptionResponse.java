package com.trade.exception.response;

import java.util.List;

public class ApiExceptionResponse {
	private final int code;
	private final String message;
	private final List<ApiExceptionResponseDetails> details;

	public ApiExceptionResponse(int code, String message, List<ApiExceptionResponseDetails> details) {
		this.code = code;
		this.message = message;
		this.details = details;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public List<ApiExceptionResponseDetails> getDetails() {
		return details;
	}
}
