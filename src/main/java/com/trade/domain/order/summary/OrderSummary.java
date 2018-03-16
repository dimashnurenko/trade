package com.trade.domain.order.summary;

import java.math.BigDecimal;

public class OrderSummary {
	private BigDecimal price;
	private BigDecimal shipping;
	private BigDecimal total;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getShipping() {
		return shipping;
	}

	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
