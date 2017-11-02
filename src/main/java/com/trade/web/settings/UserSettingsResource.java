package com.trade.web.settings;

import com.trade.common.Currency;
import com.trade.domain.user.settings.UserSettings;
import com.trade.web.user.UsersController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
@Setter
public class UserSettingsResource extends ResourceSupport {
	private Long settingsId;
	private Long userId;
	private BigDecimal exchangeRate;
	private Currency currency;
	private double percent;

	public UserSettingsResource(UserSettings entity) {
		this.settingsId = entity.getId();
		this.userId = entity.getUserId();
		this.exchangeRate = entity.getExchangeRate();
		this.currency = entity.getCurrency();
		this.percent = entity.getPercent();

		add(linkTo(methodOn(UserSettingController.class).findSetting(userId, settingsId)).withSelfRel());
		add(linkTo(methodOn(UsersController.class).findOne(userId)).withRel("user"));
	}
}
