package com.trade.domain.order;

import java.math.BigDecimal;

public class Order {
	private final Long orderId;
	private final Long groupId;
	private final String customerName;
	private final String adTitle;
	private final Long adId;
	private final BigDecimal total;
	private final Status status;

	private Order(Long orderId, Long groupId, String customerName, String adTitle, Long adId, BigDecimal total, Status status) {
		this.orderId = orderId;
		this.groupId = groupId;
		this.customerName = customerName;
		this.adTitle = adTitle;
		this.adId = adId;
		this.total = total;
		this.status = status;
	}

	public static OrderBuilder builder() {
		return new OrderBuilder();
	}

	public Long getOrderId() {
		return orderId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public Long getAdId() {
		return adId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public Status getStatus() {
		return status;
	}

	public static class OrderBuilder {
		private Long orderId;
		private Long groupId;
		private String customerName;
		private String adTitle;
		private Long adId;
		private BigDecimal total;
		private Status status;

		public OrderBuilder orderId(Long orderId) {
			this.orderId = orderId;
			return this;
		}

		public OrderBuilder groupId(Long groupId) {
			this.groupId = groupId;
			return this;
		}

		public OrderBuilder customerName(String customerName) {
			this.customerName = customerName;
			return this;
		}

		public OrderBuilder adTitle(String adTitle) {
			this.adTitle = adTitle;
			return this;
		}

		public OrderBuilder adId(Long adId) {
			this.adId = adId;
			return this;
		}

		public OrderBuilder total(BigDecimal total) {
			this.total = total;
			return this;
		}

		public OrderBuilder status(Status status) {
			this.status = status;
			return this;
		}

		public Order build() {
			return new Order(orderId, groupId, customerName, adTitle, adId, total, status);
		}
	}
}
