package com.trade.domain.order.recipient;

import com.trade.web.order.recipient.RecipientDto;

public interface RecipientsService {
	void create(Long orderId, RecipientDto recipient);

	Recipient findOneByOrderId(Long orderId);
}
