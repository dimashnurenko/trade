package com.trade.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static com.trade.order.Status.NEW;
import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@Table(name = "paid_order")
@Entity
public class Order {
	@Id
	@GeneratedValue
	@NotNull
	private Long id;

	@Column(name = "user_id")
	@NotNull
	private Long userId;

	@Column
	private String link;

	@Column
	private String color;

	@Column
	private String size;

	@Column
	@Enumerated(value = STRING)
	private Status status = NEW;
}
