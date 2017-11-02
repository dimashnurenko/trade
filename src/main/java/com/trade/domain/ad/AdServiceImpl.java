package com.trade.domain.ad;

import com.trade.domain.user.settings.UserSettings;
import com.trade.domain.user.settings.UserSettingsRepo;
import com.trade.parser.WebContentParser;
import com.trade.parser.WebContentParserFactory;
import com.trade.web.ad.AdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AdServiceImpl implements AdService {

	private final UserSettingsRepo buyerSettingsRepo;
	private final AdRepo adRepo;
	private final WebContentParserFactory parserRegistry;

	@Autowired
	public AdServiceImpl(UserSettingsRepo buyerSettingsRepo, AdRepo adRepo, WebContentParserFactory parserRegistry) {
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
		List<UserSettings> buyerSettings = buyerSettingsRepo.findAllByUserId(buyerId);
		//TODO think how to get settings
		UserSettings settingsEntity = buyerSettings.get(0);

		return priceWithoutPercent.add(priceWithoutPercent.multiply(new BigDecimal(settingsEntity.getPercent())));
	}
}
