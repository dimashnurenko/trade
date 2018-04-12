package com.trade.exception;

import com.trade.exception.client.ApiException;
import com.trade.exception.client.ApiExceptionDetails;
import com.trade.exception.client.ExceptionReason;

import java.util.Arrays;

public class CoreAPIException extends ApiException {
	public CoreAPIException(ExceptionReason reason, ApiExceptionDetails... details) {
		super(reason, Arrays.asList(details));
	}
}
