package com.trade.domain.events.product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductCreatedEvent {
	private final Long productId;
	private final Long userId;
}
