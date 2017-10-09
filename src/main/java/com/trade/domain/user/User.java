package com.trade.domain.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Table(name = "user")
public final class User {
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;

	@Column
	@NotNull
	private String phone;

	@Column
	@NotNull
	private String password;

	@OneToMany(fetch = EAGER, mappedBy = "user")
	private List<UserRole> roles = new ArrayList<>();
}
