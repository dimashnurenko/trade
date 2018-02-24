package com.trade.domain.order;

import com.trade.common.audit.CreateAudit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity extends CreateAudit {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "product_id")
	private Long productId;

	@Column
	private int quantity;

	@Enumerated(STRING)
	@Column
	private OrderStatus status;
}
