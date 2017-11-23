package com.trade.domain.ad;

import com.trade.web.ad.AdDto;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

class AdPriceCalculator {

	static BigDecimal calculatePrice(AdDto dto, BigDecimal priceWithoutPercent) {
		BigDecimal priceInUAH = priceWithoutPercent.multiply(dto.getExchangeRate());
		return priceInUAH.add(priceInUAH.multiply(valueOf(dto.getPercent())))
		                 .setScale(2, HALF_UP);
	}
}
