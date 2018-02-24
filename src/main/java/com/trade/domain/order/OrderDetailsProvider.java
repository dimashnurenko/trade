package com.trade.domain.order;

import com.trade.common.exception.ResourceNotFoundException;
import com.trade.domain.order.recipient.RecipientsService;
import com.trade.domain.order.summary.SummaryService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderDetailsProvider {
	private final OrderRepo orderRepo;
	private final OrdersMapper mapper;
	private final SummaryService summaryService;
	private final RecipientsService recipientsService;

	public OrderDetailsProvider(OrderRepo orderRepo,
	                            OrdersMapper mapper,
	                            SummaryService summaryService,
	                            RecipientsService recipientsService) {
		this.orderRepo = orderRepo;
		this.mapper = mapper;
		this.summaryService = summaryService;
		this.recipientsService = recipientsService;
	}

	public Order findOne(Long orderId) {
		OrderEntity orderEntity = Optional.ofNullable(orderRepo.findOne(orderId))
		                                  .orElseThrow(() -> new ResourceNotFoundException(orderId, "order"));

		Order order = mapper.toModel(orderEntity);
		order.setSummary(summaryService.findOneByOrderId(orderId));
		order.setRecipient(recipientsService.findOneByOrderId(orderId));
		return order;
	}
}
