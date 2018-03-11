package com.trade.domain.order.recipient;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "recipients")
public class RecipientEntity {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "order_id")
	private Long orderId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column
	private String address1;
	@Column
	private String address2;
	@Column
	private String phone;
	@Column
	private String email;
	@Column
	private String country;
	@Column
	private String zip;
}
