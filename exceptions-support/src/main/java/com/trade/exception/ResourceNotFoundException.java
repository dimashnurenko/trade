package com.trade.exception;

import com.trade.exception.client.ApiException;
import com.trade.exception.client.ApiExceptionDetails;

import java.util.Arrays;

import static com.trade.exception.BaseExceptionReason.RESOURCE_NOT_FOUND;

public class ResourceNotFoundException extends ApiException {
	public ResourceNotFoundException(ApiExceptionDetails... details) {
		super(RESOURCE_NOT_FOUND, Arrays.asList(details));
	}
}
