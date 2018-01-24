package com.trade.domain.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Product {

	private Long id;
	private String title;
	private String description;
	private LocalDateTime createdDate;
	private Long createdBy;
	private BigDecimal price;
	private Long defaultImageId;
	private ProductStatus status;
}
