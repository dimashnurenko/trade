package com.trade.domain.events.order;

public class OrderCreatedEvent {
	private final Long customerId;
	private final Long orderId;
	private final Long productId;
	private final int quantity;

	public OrderCreatedEvent(Long customerId, Long orderId, Long productId, int quantity) {
		this.customerId = customerId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}
}
