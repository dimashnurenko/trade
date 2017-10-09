package com.trade.web.security.token;

import lombok.Data;

@Data
public class AccessToken {
	private Long userId;
	private String token;
}
