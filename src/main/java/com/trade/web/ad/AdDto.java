package com.trade.web.ad;

import java.math.BigDecimal;

public class AdDto {
	private String link;
	private BigDecimal exchangeRate;
	private double percent;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
}
