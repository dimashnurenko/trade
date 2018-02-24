package com.trade.security.auth.token;

import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;

@Getter
@Setter
public class AuthTokenDto {
	private String token;
	private long expirationDate;

	public AuthTokenDto(AuthToken token) {
		this.token = token.getToken();
		this.expirationDate = token.getExpirationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
}
