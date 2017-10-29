package com.trade.domain.ad;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AdEntity {
	private Long addId;
	private String link;
	private String title;
	private BigDecimal price;
	private String imageUrl;
}
