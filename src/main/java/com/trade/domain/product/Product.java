package com.trade.domain.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {

	private Long id;
	private String title;
	private String description;
	private LocalDateTime createdDate;
	private Long createdBy;
	private BigDecimal price;
	private Long defaultImageId;
	private ProductStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getDefaultImageId() {
		return defaultImageId;
	}

	public void setDefaultImageId(Long defaultImageId) {
		this.defaultImageId = defaultImageId;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}
}
