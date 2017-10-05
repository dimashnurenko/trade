package com.trade.domain.order;

import com.trade.common.ResourceNotFoundException;
import com.trade.web.order.OrderDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service(value = "orderService") class OrderServiceImpl implements OrderService {
	private static final Logger log = Logger.getLogger(OrderServiceImpl.class);

	private final OrderRepo orderRepo;
	private final OrderMapper mapper;

	OrderServiceImpl(OrderRepo orderRepo, OrderMapper mapper) {
		this.orderRepo = orderRepo;
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrderDto> findByFilter(OrdersFilter filter) {
		return orderRepo.findAll()
		                .stream()
		                .map(mapper::map)
		                .collect(toList());
	}

	@Override
	@Transactional(readOnly = true)
	public OrderDto findOne(Long id) {
		return ofNullable(id).map(orderRepo::findOne)
		                     .map(mapper::map)
		                     .orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Override
	@Transactional
	public long createOrder(OrderDto dto) {
		Long orderId = saveOrUpdate(dto);
		log.debug(String.format("Order with id: %s created.", orderId));
		return orderId;
	}

	private long saveOrUpdate(OrderDto dto) {
		Order order = orderRepo.save(mapper.map(dto));
		return order.getId();
	}

	@Override
	@Transactional
	public void updateOrder(OrderDto dto) {
		Long orderId = saveOrUpdate(dto);
		log.debug(String.format("Order with id: %s updated.", orderId));
	}
}
