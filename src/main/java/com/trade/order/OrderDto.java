package com.trade.order;

import lombok.Data;

import static com.trade.order.Status.NEW;

@Data class OrderDto {
	private Long id;
	private Long userId;
	private String link;
	private String color;
	private String size;
	private Status status = NEW;
}
