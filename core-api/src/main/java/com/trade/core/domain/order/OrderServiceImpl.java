package com.trade.core.domain.order;

import com.trade.web.order.CreateOrderDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderServiceImpl implements OrderService {

	private final OrderCreator orderCreator;
	private final OrderDetailsProvider orderDetailsProvider;

	public OrderServiceImpl(OrderCreator orderCreator,
	                        OrderDetailsProvider orderDetailsProvider) {
		this.orderCreator = orderCreator;
		this.orderDetailsProvider = orderDetailsProvider;
	}

	@Override
	@Transactional
	public Long create(CreateOrderDto dto, Long customerId) {
		return orderCreator.create(dto, customerId);
	}

	@Override
	@Transactional(readOnly = true)
	public Order findOne(Long orderId) {
		return orderDetailsProvider.findOne(orderId);
	}
}
