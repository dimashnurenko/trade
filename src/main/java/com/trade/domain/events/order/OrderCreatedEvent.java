package com.trade.domain.events.order;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderCreatedEvent {
	private final Long customerId;
	private final Long orderId;
	private final Long productId;
	private final int quantity;
}
