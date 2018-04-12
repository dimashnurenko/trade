package com.trade.exception.client;

import java.util.Arrays;

import static com.trade.exception.BaseExceptionReason.BAD_AUTH;

public class BadAuthException extends ApiException {
	public BadAuthException(ApiExceptionDetails... details) {
		super(BAD_AUTH, Arrays.asList(details));
	}
}
