package com.trade.security.auth;

import com.trade.security.token.AccessToken;

public interface AuthenticationService {
	AccessToken authenticate(AuthUserDto dto);

	void logout(String token);
}
