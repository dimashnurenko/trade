package com.trade.domain.order;

import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
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
}
