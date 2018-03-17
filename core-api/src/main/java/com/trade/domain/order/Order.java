package com.trade.domain.order;

import com.trade.domain.order.recipient.Recipient;
import com.trade.domain.order.summary.OrderSummary;

public class Order {
	private Long id;
	private Long productId;
	private int quantity;
	private OrderStatus status;
	private Recipient recipient;
	private OrderSummary summary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public OrderSummary getSummary() {
		return summary;
	}

	public void setSummary(OrderSummary summary) {
		this.summary = summary;
	}
}
