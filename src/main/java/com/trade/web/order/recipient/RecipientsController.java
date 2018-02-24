package com.trade.web.order.recipient;

import com.trade.domain.order.recipient.RecipientsService;
import com.trade.web.user.LoggedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/orders/{orderId}/recipients")
public class RecipientsController {
	private final RecipientsService recipientsService;

	public RecipientsController(RecipientsService recipientsService) {
		this.recipientsService = recipientsService;
	}

	@PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity create(@PathVariable Long orderId, @RequestBody RecipientDto recipient, LoggedUser user) {
		recipientsService.create(orderId, recipient);
		return ok().build();
	}
}
