package com.trade.core.domain.order;

import com.trade.core.domain.order.recipient.RecipientsService;
import com.trade.core.domain.order.summary.SummaryService;
import com.trade.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.trade.exception.client.ApiExceptionDetails.exceptionDetails;

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
		                                  .orElseThrow(() -> new ResourceNotFoundException(exceptionDetails("resource.not.found",
		                                                                                                    new Object[]{"order", orderId})));

		Order order = mapper.toModel(orderEntity);
		order.setSummary(summaryService.findOneByOrderId(orderId));
		order.setRecipient(recipientsService.findOneByOrderId(orderId));
		return order;
	}
}
