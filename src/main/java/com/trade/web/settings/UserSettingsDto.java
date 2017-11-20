package com.trade.web.settings;

import com.trade.common.Currency;

import java.math.BigDecimal;

public class UserSettingsDto {
	private BigDecimal exchangeRate;
	private Currency currency;
	private double percent;

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
}
