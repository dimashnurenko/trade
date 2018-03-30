package com.trade.core.domain.order.recipient;

import com.trade.web.order.recipient.RecipientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipientsMapper {
	Recipient toModel(RecipientEntity entity);

	RecipientEntity toEntity(RecipientDto recipient);
}
