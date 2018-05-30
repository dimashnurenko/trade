package com.trade.web.product;

import com.trade.core.domain.product.Product;
import com.trade.core.domain.product.ProductStatus;
import com.trade.web.image.ImagesController;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ProductResource extends ResourceSupport {
	private Long productId;
	private String title;
	private String description;
	private long createdDate;
	private Long createdBy;
	private BigDecimal price;
	private Long defaultImageId;
	private ProductStatus status;

	public ProductResource(Product product) {
		this.productId = product.getId();
		this.title = product.getTitle();
		this.description = product.getDescription();
		this.createdDate = product.getCreatedDate().toLocalTime().getSecond();
		this.createdBy = product.getCreatedBy();
		this.price = product.getPrice();
		this.defaultImageId = product.getDefaultImageId();
		this.status = product.getStatus();

		add(linkTo(methodOn(ImagesController.class).upload(productId, null)).withRel("upload-image"));
		add(linkTo(methodOn(ImagesController.class).images(productId)).withRel("product-images"));
		if (defaultImageId != null) {
			add(linkTo(methodOn(ImagesController.class).image(productId, defaultImageId)).withRel("product-image"));
		}
	}

	public Long getProductId() {
		return productId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Long getDefaultImageId() {
		return defaultImageId;
	}

	public ProductStatus getStatus() {
		return status;
	}
}
