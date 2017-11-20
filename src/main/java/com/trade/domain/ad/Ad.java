package com.trade.domain.ad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "ad")
public class Ad {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "group_id")
	private Long groupId;
	@Column
	private String link;
	@Column
	private String title;
	@Column
	private BigDecimal price;
	@Column(name = "image_url")
	private String imageUrl;
	@Enumerated(value = STRING)
	@Column(name = "status")
	private AdStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public AdStatus getStatus() {
		return status;
	}

	public void setStatus(AdStatus status) {
		this.status = status;
	}
}
