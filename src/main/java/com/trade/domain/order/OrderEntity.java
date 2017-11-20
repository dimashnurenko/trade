package com.trade.domain.order;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "paid_order")
public class OrderEntity {
	@Id
	@GeneratedValue
	private Long id;
	@CreatedBy
	@Column(name = "creator_id")
	private Long creatorId;
	@LastModifiedBy
	@Column(name = "updater_id")
	private Long updaterId;
	@Column(name = "ad_id")
	private Long adId;
	@Column
	private BigDecimal total;
	@Enumerated(STRING)
	@Column
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
