package com.trade.core.domain.order;

import com.trade.web.order.CreateOrderDto;

public interface OrderService {
	Long create(CreateOrderDto dto, Long customerId);

	Order findOne(Long orderId);
}
