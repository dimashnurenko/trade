package com.trade.exception.client;

import java.util.Collections;
import java.util.List;

public class ApiException extends RuntimeException {
	private final ExceptionReason reason;
	private final List<ApiExceptionDetails> details;
	private final String[] args;

	protected ApiException(ExceptionReason reason, List<ApiExceptionDetails> details, String... args) {
		this.reason = reason;
		this.details = details;
		this.args = args == null ? new String[]{} : args;
	}

	protected ApiException(ExceptionReason reason, List<ApiExceptionDetails> details) {
		this(reason, details, new String[]{});
	}

	protected ApiException(ExceptionReason reason) {
		this(reason, Collections.emptyList(), new String[]{});
	}

	public static ApiException apiException(ExceptionReason reason) {
		return new ApiException(reason, Collections.emptyList());
	}

	public static ApiException apiException(ExceptionReason reason, List<ApiExceptionDetails> details) {
		return new ApiException(reason, details);
	}

	public static ApiException apiException(ExceptionReason reason, String... args) {
		return new ApiException(reason, Collections.emptyList(), args);
	}

	public static ApiException apiException(ExceptionReason reason, List<ApiExceptionDetails> details, String... args) {
		return new ApiException(reason, details, args);
	}

	public ExceptionReason getReason() {
		return reason;
	}

	public List<ApiExceptionDetails> getDetails() {
		return details;
	}

	public String[] getArgs() {
		return args;
	}
}
