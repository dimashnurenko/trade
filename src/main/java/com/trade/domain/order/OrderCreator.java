package com.trade.domain.order;

import com.google.common.eventbus.EventBus;
import com.trade.web.order.CreateOrderDto;
import org.springframework.stereotype.Component;

import static com.trade.domain.events.order.OrderCreatedEvent.Builder.builder;
import static com.trade.domain.order.OrderStatus.NEW;

@Component
public class OrderCreator {
	private final EventBus eventBus;
	private final OrderRepo orderRepo;

	public OrderCreator(EventBus eventBus, OrderRepo orderRepo) {
		this.eventBus = eventBus;
		this.orderRepo = orderRepo;
	}

	public Long create(CreateOrderDto dto, Long customerId) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setProductId(dto.getProductId());
		orderEntity.setStatus(NEW);
		orderEntity.setQuantity(dto.getQuantity());

		OrderEntity order = orderRepo.save(orderEntity);

		eventBus.post(builder().customerId(customerId)
		                       .orderId(order.getId())
		                       .productId(dto.getProductId())
		                       .quantity(dto.getQuantity())
		                       .build());
		return order.getId();
	}
}
