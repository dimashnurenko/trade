package com.trade.core.domain.order.recipient;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.trade.core.domain.user.UserRepo;
import com.trade.core.domain.user.model.UserEntity;
import com.trade.core.domain.events.order.OrderCreatedEvent;
import com.trade.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import static com.trade.exception.client.ApiExceptionDetails.exceptionDetails;

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
		UserEntity user = userRepo.findById(event.getCustomerId())
		                          .orElseThrow(() -> new ResourceNotFoundException(exceptionDetails("resource.not.found", new String[]{"user"})));

		RecipientEntity recipient = new RecipientEntity();
		recipient.setPhone(user.getPhone());
		recipient.setFirstName(user.getName());
		recipient.setOrderId(event.getOrderId());

		recipientRepo.save(recipient);
	}
}
