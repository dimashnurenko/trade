package com.trade.domain.ad;

import com.trade.domain.group.GroupRepo;
import com.trade.domain.user.settings.UserSettingsRepo;
import com.trade.parser.WebContentParser;
import com.trade.parser.WebContentParserFactory;
import com.trade.web.ad.AdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

@Component
public class AdServiceImpl implements AdService {

	private final UserSettingsRepo buyerSettingsRepo;
	private final AdRepo adRepo;
	private final GroupRepo groupRepo;
	private final WebContentParserFactory parserRegistry;

	@Autowired
	public AdServiceImpl(UserSettingsRepo buyerSettingsRepo, AdRepo adRepo, GroupRepo groupRepo, WebContentParserFactory parserRegistry) {
		this.buyerSettingsRepo = buyerSettingsRepo;
		this.adRepo = adRepo;
		this.groupRepo = groupRepo;
		this.parserRegistry = parserRegistry;
	}

	@Override
	public Ad create(Long groupId, AdDto dto) {
		WebContentParser contentParser = parserRegistry.createParser(dto.getLink());

		Ad entity = new Ad();
		entity.setGroupId(groupId);
		entity.setImageUrl(contentParser.getImageUrl());
		entity.setLink(dto.getLink());
		entity.setTitle(contentParser.getTitle());
		entity.setPrice(calculatePrice(dto, contentParser.getPrice()));
		return adRepo.save(entity);
	}

	private BigDecimal calculatePrice(AdDto dto, BigDecimal priceWithoutPercent) {
		BigDecimal priceInUAH = priceWithoutPercent.multiply(dto.getExchangeRate());
		return priceInUAH.add(priceInUAH.multiply(valueOf(dto.getPercent())))
		                 .setScale(2, HALF_UP);
	}
}
