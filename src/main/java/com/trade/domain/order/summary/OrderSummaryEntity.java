package com.trade.domain.order.summary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_summary")
public class OrderSummaryEntity {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "order_id")
	private Long orderId;
	@Column
	private BigDecimal price;
	@Column
	private BigDecimal shipping;
	@Column
	private BigDecimal total;
}
