package com.trade.security.auth;

import com.trade.security.auth.token.AuthToken;
import com.trade.web.auth.UserInfoDto;

public interface AuthenticationService {
	AuthToken authenticate(AuthUserDto dto);

	UserInfoDto authenticate(String token);

	void logout(String token);
}
