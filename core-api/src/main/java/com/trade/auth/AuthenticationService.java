package com.trade.auth;

import com.trade.auth.token.AuthToken;
import com.trade.auth.web.UserInfoDto;

public interface AuthenticationService {
	AuthToken authenticate(AuthUserDto dto);

	UserInfoDto authenticate(String token);

	void logout(String token);
}
