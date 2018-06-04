package com.trade.exception;

import com.trade.exception.client.ApiException;
import com.trade.exception.client.ApiExceptionDetails;

import java.util.Arrays;

import static com.trade.exception.BaseExceptionReason.RESOURCE_NOT_FOUND;
import static java.util.Collections.singletonList;

public class ResourceNotFoundException extends ApiException {
	public ResourceNotFoundException(ApiExceptionDetails... details) {
		super(RESOURCE_NOT_FOUND, Arrays.asList(details));
	}

	public ResourceNotFoundException(String resourceName, Long id) {
		super(RESOURCE_NOT_FOUND, singletonList(ApiExceptionDetails.exceptionDetails("resource.not.found", new Object[]{resourceName, id})));
	}

	public ResourceNotFoundException(String resourceName, String id) {
		super(RESOURCE_NOT_FOUND, singletonList(ApiExceptionDetails.exceptionDetails("resource.not.found", new Object[]{resourceName, id})));
	}
}
