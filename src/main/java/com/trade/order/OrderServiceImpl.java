package com.trade.order;

import com.trade.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

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
		                     .orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
