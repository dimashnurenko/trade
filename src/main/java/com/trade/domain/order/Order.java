package com.trade.domain.order;

import com.trade.domain.order.recipient.Recipient;
import com.trade.domain.order.summary.OrderSummary;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order {
	private Long id;
	private Long productId;
	private int quantity;
	private OrderStatus status;
	private Recipient recipient;
	private OrderSummary summary;
}
