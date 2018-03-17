package com.trade.domain.order.recipient;

import com.trade.web.order.recipient.RecipientDto;
import org.springframework.stereotype.Component;

@Component
public class RecipientServiceImpl implements RecipientsService {
	private final RecipientRepo recipientRepo;
	private final RecipientsMapper mapper;

	public RecipientServiceImpl(RecipientRepo recipientRepo, RecipientsMapper mapper) {
		this.recipientRepo = recipientRepo;
		this.mapper = mapper;
	}

	@Override
	public void create(Long orderId, RecipientDto recipient) {
		recipientRepo.save(mapper.toEntity(recipient));
	}

	@Override
	public Recipient findOneByOrderId(Long orderId) {
		return mapper.toModel(recipientRepo.findOneByOrderId(orderId));
	}
}
