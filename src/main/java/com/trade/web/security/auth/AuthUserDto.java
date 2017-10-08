package com.trade.web.security.auth;

import lombok.Data;

@Data
public class AuthUserDto {
	private String phone;
	private String password;
}
