package com.trade.domain.order.recipient;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.trade.domain.events.order.OrderCreatedEvent;
import com.trade.domain.order.recipient.RecipientEntity;
import com.trade.domain.order.recipient.RecipientRepo;
import com.trade.domain.user.UserEntity;
import com.trade.domain.user.UserRepo;
import org.springframework.stereotype.Component;

@Component("createOrderHandlerRecipient")
public class CreateOrderHandler {

	private final UserRepo userRepo;
	private final RecipientRepo recipientRepo;

	public CreateOrderHandler(EventBus eventBus, UserRepo userRepo, RecipientRepo recipientRepo) {
		this.userRepo = userRepo;
		this.recipientRepo = recipientRepo;

		eventBus.register(this);
	}

	@Subscribe
	public void onOrderCreated(OrderCreatedEvent event) {
		UserEntity user = userRepo.findOne(event.getCustomerId());

		RecipientEntity recipient = new RecipientEntity();
		recipient.setPhone(user.getPhone());
		recipient.setFirstName(user.getName());
		recipient.setOrderId(event.getOrderId());

		recipientRepo.save(recipient);
	}
}
