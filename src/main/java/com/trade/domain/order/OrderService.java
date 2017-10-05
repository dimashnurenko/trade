package com.trade.domain.order;

import com.trade.web.order.OrderDto;

import java.util.List;

public interface OrderService {

	List<OrderDto> findByFilter(OrdersFilter filter);

	OrderDto findOne(Long id);

	long createOrder(OrderDto order);

	void updateOrder(OrderDto order);
}
