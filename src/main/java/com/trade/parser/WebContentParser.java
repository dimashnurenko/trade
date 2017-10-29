package com.trade.parser;

import java.math.BigDecimal;

public interface WebContentParser {
	String getImageUrl();

	String getTitle();

	BigDecimal getPrice();
}
