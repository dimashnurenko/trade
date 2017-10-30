package com.trade.domain.ad;

import com.trade.domain.buyer.settings.BuyerSettingsEntity;
import com.trade.domain.buyer.settings.BuyerSettingsRepo;
import com.trade.parser.WebContentParser;
import com.trade.parser.WebContentParserFactory;
import com.trade.web.ad.AdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AdServiceImpl implements AdService {

	private final BuyerSettingsRepo buyerSettingsRepo;
	private final AdRepo adRepo;
	private final WebContentParserFactory parserRegistry;

	@Autowired
	public AdServiceImpl(BuyerSettingsRepo buyerSettingsRepo, AdRepo adRepo, WebContentParserFactory parserRegistry) {
		this.buyerSettingsRepo = buyerSettingsRepo;
		this.adRepo = adRepo;
		this.parserRegistry = parserRegistry;
	}

	@Override
	public AdEntity create(Long buyerId, AdDto dto) {
		WebContentParser contentParser = parserRegistry.createParser(dto.getLink());

		AdEntity entity = new AdEntity();
		entity.setImageUrl(contentParser.getImageUrl());
		entity.setLink(dto.getLink());
		entity.setTitle(contentParser.getTitle());
		entity.setPrice(calculatePrice(buyerId, contentParser.getPrice()));
		return adRepo.save(entity);
	}

	private BigDecimal calculatePrice(Long buyerId, BigDecimal priceWithoutPercent) {
		List<BuyerSettingsEntity> buyerSettings = buyerSettingsRepo.findAllByBuyerId(buyerId);
		//TODO think how to get settings
		BuyerSettingsEntity settingsEntity = buyerSettings.get(0);

		return priceWithoutPercent.add(priceWithoutPercent.multiply(new BigDecimal(settingsEntity.getPercent())));
	}
}
