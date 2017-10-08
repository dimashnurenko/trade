package com.trade.web.security.token;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "auth_token")
public class AuthToken {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "user_id")
	@NotNull
	private Long userId;

	@Column
	@NotNull
	private String token;

	@Column(name = "expiration_date")
	@NotNull
	private Date expirationDate;
}
