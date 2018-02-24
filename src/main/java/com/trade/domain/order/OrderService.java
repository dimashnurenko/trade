package com.trade.domain.order;

import com.trade.web.order.CreateOrderDto;

public interface OrderService {
	Long create(CreateOrderDto dto);

	Order findOne(Long orderId);
}
