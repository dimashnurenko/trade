package com.trade.domain.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "user")
public final class User {
	@Id
	@GeneratedValue
	@NotNull
	private Long id;

	@Column
	private String name;

	@Column
	@NotNull
	private String phone;
}
