package com.trade.order;

import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service class OrderServiceImpl implements OrderService {
	private final OrderRepo orderRepo;
	private final OrderMapper mapper;

	OrderServiceImpl(OrderRepo orderRepo, OrderMapper mapper) {
		this.orderRepo = orderRepo;
		this.mapper = mapper;
	}

	@Override
	public OrderDto findOne(Long id) {
		return ofNullable(id).map(orderRepo::findOne)
		                     .map(mapper::map)
		                     .orElseThrow(() -> new NotFoundException(format("The order with id: %s not found", id)));
	}
}
