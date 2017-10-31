package com.trade.web.buyer;

import com.trade.domain.buyer.BuyerEntity;
import com.trade.domain.buyer.settings.BuyerSettingsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuyerMapper {
	BuyerEntity map(BuyerDto dto);

	BuyerSettingsEntity map(BuyerSettingsDto dto);
}
