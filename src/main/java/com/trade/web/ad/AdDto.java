package com.trade.web.ad;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AdDto {
	private String link;
	private BigDecimal exchangeRate;
	private double percent;
}
