package com.trade.ws;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AdMessage {
	private Long id;
	private Long groupId;
	private String link;
	private String title;
	private BigDecimal price;
	private String imageUrl;
}
