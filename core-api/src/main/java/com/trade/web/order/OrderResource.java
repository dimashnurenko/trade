package com.trade.web.order;

import com.trade.core.domain.order.Order;
import org.springframework.hateoas.ResourceSupport;

public class OrderResource extends ResourceSupport {
	private Order order;

	public OrderResource(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}
}
