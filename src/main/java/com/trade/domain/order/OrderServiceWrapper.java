package com.trade.domain.order;

import com.trade.validator.Validator;
import com.trade.web.order.OrderDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "orderServiceWrapper")
public class OrderServiceWrapper implements OrderService {

	private final OrderService service;
	private final Validator<OrderDto> orderValidator;

	public OrderServiceWrapper(@Qualifier("orderService") OrderService service,
	                           @Qualifier("createOrderValidator") Validator<OrderDto> orderValidator) {
		this.service = service;
		this.orderValidator = orderValidator;
	}

	@Override
	public List<OrderDto> findByFilter(OrdersFilter filter) {
		return service.findByFilter(filter);
	}

	@Override
	public OrderDto findOne(Long id) {
		return service.findOne(id);
	}

	@Override
	public long createOrder(OrderDto order) {
		orderValidator.validate(order);
		return service.createOrder(order);
	}

	@Override
	public void updateOrder(OrderDto order) {
		orderValidator.validate(order);
		service.updateOrder(order);
	}
}
