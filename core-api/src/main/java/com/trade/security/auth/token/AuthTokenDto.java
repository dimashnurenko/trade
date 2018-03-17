package com.trade.security.auth.token;

import java.time.ZoneId;

public class AuthTokenDto {
	private final String token;
	private final long expirationDate;

	public AuthTokenDto(AuthToken token) {
		this.token = token.getToken();
		this.expirationDate = token.getExpirationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	public String getToken() {
		return token;
	}

	public long getExpirationDate() {
		return expirationDate;
	}
}
