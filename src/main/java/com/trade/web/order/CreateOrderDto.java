package com.trade.web.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderDto {
	private Long productId;
	private int quantity;
}
