package com.trade.domain.order.summary;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.trade.domain.events.order.OrderCreatedEvent;
import com.trade.domain.product.Product;
import com.trade.domain.product.ProductService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreateOrderHandler {
	private final ProductService productService;
	private final SummaryRepo summaryRepo;

	public CreateOrderHandler(ProductService productService, SummaryRepo summaryRepo, EventBus eventBus) {
		this.productService = productService;
		this.summaryRepo = summaryRepo;

		eventBus.register(this);
	}

	@Subscribe
	public void onOrderCreated(OrderCreatedEvent event) {
		Product product = productService.findById(event.getProductId());

		OrderSummaryEntity summaryEntity = new OrderSummaryEntity();
		summaryEntity.setOrderId(event.getOrderId());
		BigDecimal price = product.getPrice();
		summaryEntity.setPrice(price);
		summaryEntity.setTotal(price.multiply(BigDecimal.valueOf(event.getQuantity())));

		summaryRepo.save(summaryEntity);
	}
}
