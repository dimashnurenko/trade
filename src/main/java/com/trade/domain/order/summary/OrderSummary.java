package com.trade.domain.order.summary;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderSummary {
	private BigDecimal price;
	private BigDecimal shipping;
	private BigDecimal total;
}
