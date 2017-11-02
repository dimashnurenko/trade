package com.trade.web.settings;

import com.trade.common.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UserSettingsDto {
	private BigDecimal exchangeRate;
	private Currency currency;
	private double percent;
}
