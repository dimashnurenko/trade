package com.trade.web.buyer;

import com.trade.common.Currency;
import com.trade.domain.buyer.settings.BuyerSettingsEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
@Setter
public class BuyerSettingsResource extends ResourceSupport {
	private Long buyerSettingsId;
	private Long buyerId;
	private BigDecimal exchangeRate;
	private Currency currency;
	private double percent;

	public BuyerSettingsResource(BuyerSettingsEntity entity) {
		this.buyerSettingsId = entity.getId();
		this.buyerId = entity.getBuyerId();
		this.exchangeRate = entity.getExchangeRate();
		this.currency = entity.getCurrency();
		this.percent = entity.getPercent();

		add(linkTo(methodOn(BuyerSettingController.class).findSetting(buyerId, buyerSettingsId)).withSelfRel());
		add(linkTo(methodOn(BuyerController.class).findOne(buyerId)).withRel("buyer"));
	}
}
