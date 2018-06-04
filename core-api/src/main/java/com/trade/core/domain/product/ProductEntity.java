package com.trade.core.domain.product;

import com.trade.core.common.audit.CreateAudit;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static com.trade.core.domain.product.ProductStatus.NEW;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity extends CreateAudit {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column
	private String title;

	@Column
	private String description;

	@Column
	private BigDecimal price;

	@Column
	@Enumerated(value = STRING)
	private ProductStatus status = NEW;

	@Column(name = "feed_id")
	private Long feedId;

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public Long getFeedId() {
		return feedId;
	}

	public void setFeedId(Long feedId) {
		this.feedId = feedId;
	}
}
