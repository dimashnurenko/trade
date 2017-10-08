package com.trade.web.security.auth;

import com.trade.web.security.token.AuthTokenDto;

public interface AuthenticationService {
	AuthTokenDto authenticate(AuthUserDto dto);

	void logout(Long userId);
}
