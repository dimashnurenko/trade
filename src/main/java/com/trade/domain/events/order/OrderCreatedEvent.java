package com.trade.domain.events.order;

public class OrderCreatedEvent {
	private final Long customerId;
	private final Long orderId;
	private final Long productId;
	private final int quantity;

	private OrderCreatedEvent(Long customerId, Long orderId, Long productId, int quantity) {
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

	public static final class Builder {
		private Long customerId;
		private Long orderId;
		private Long productId;
		private int quantity;

		private Builder() {
		}

		public static Builder builder() {
			return new Builder();
		}

		public Builder customerId(Long customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder orderId(Long orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder productId(Long productId) {
			this.productId = productId;
			return this;
		}

		public Builder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public OrderCreatedEvent build() {
			return new OrderCreatedEvent(customerId, orderId, productId, quantity);
		}
	}
}
