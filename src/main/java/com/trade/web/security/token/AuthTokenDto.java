package com.trade.web.security.token;

import lombok.Data;

@Data
public class AuthTokenDto {
	private Long userId;
	private String token;
}
