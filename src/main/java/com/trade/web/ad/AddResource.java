package com.trade.web.ad;

import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

public class AddResource extends ResourceSupport {
	private Long addId;
	private String link;
	private String title;
	private BigDecimal price;
	private String imageUrl;

	public void setAddId(Long addId) {
		this.addId = addId;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getAddId() {
		return addId;
	}

	public String getLink() {
		return link;
	}

	public String getTitle() {
		return title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getImageUrl() {
		return imageUrl;
	}
}
