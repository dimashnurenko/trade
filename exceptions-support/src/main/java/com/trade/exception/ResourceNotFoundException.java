package com.trade.exception;

import static java.lang.String.format;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(Long id, String resourceName) {
		super(id == null ? "Resource id is null" : format("Resource %s with id %s not found", resourceName, id));
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
