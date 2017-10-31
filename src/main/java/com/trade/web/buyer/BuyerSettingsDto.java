package com.trade.web.buyer;

import com.trade.common.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BuyerSettingsDto {
	private Long buyerId;
	private BigDecimal exchangeRate;
	private Currency currency;
	private double percent;
}
