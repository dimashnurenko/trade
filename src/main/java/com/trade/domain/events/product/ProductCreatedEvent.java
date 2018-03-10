package com.trade.domain.events.product;

public class ProductCreatedEvent {
	private final Long productId;
	private final Long userId;

	public ProductCreatedEvent(Long productId, Long userId) {
		this.productId = productId;
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public Long getUserId() {
		return userId;
	}
}
