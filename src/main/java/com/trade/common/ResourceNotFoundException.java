package com.trade.common;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(Long id) {
		super(id == null ? "Resource id is null" : String.format("Resource with id %s not found", id));
	}
}
