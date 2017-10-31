package com.trade.domain.buyer;

import com.trade.domain.buyer.settings.BuyerSettingsEntity;
import com.trade.web.buyer.BuyerDto;
import com.trade.web.buyer.BuyerSettingsDto;

public interface BuyerService {
	BuyerEntity create(BuyerDto dto);

	BuyerSettingsEntity create(BuyerSettingsDto dto);
}
