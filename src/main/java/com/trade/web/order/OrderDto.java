package com.trade.web.order;

import com.trade.domain.order.Status;
import lombok.Data;

import static com.trade.domain.order.Status.NEW;

@Data
public final class OrderDto {
	private Long id;
	private Long userId;
	private String link;
	private String color;
	private String size;
	private Status status = NEW;
}
