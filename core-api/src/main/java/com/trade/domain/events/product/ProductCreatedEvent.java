package com.trade.domain.events.product;

public class ProductCreatedEvent {
	private final Long productId;
	private final Long userId;

	private ProductCreatedEvent(Long productId, Long userId) {
		this.productId = productId;
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public Long getUserId() {
		return userId;
	}

	public static final class Builder {
		private Long productId;
		private Long userId;

		private Builder() {
		}

		public static Builder builder() {
			return new Builder();
		}

		public Builder productId(Long productId) {
			this.productId = productId;
			return this;
		}

		public Builder userId(Long userId) {
			this.userId = userId;
			return this;
		}

		public ProductCreatedEvent build() {
			return new ProductCreatedEvent(productId, userId);
		}
	}
}
