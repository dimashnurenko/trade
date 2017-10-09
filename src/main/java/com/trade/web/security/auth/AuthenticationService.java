package com.trade.web.security.auth;

import com.trade.web.security.token.AccessToken;

public interface AuthenticationService {
	AccessToken authenticate(AuthUserDto dto);

	void logout(String token);
}
