package com.trade.parser;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component class GapUkContentParser implements WebContentParser {
	@Override
	public String getImageUrl() {
		return null;
	}

	@Override
	public String getTitle() {
		return null;
	}

	@Override
	public BigDecimal getPrice() {
		return null;
	}
}
