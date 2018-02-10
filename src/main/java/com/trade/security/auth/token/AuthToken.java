package com.trade.security.auth.token;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "auth_token")
public class AuthToken {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column
	private String token;
	@NotNull
	@Column(name = "expiration_date")
	private LocalDateTime expirationDate;
	@NotNull
	@Column(name = "user_id")
	private Long userId;
}
