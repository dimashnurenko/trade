package com.trade.domain.buyer.settings;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuyerSettingsRepo extends CrudRepository<BuyerSettingsEntity, Long> {
	List<BuyerSettingsEntity> findAllByBuyerId(Long buyerId);
}
