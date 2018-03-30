package com.trade.auth;

import com.trade.auth.user.model.UserEntity;
import com.trade.auth.user.UserRepo;
import com.trade.auth.token.AuthToken;
import com.trade.auth.token.AuthTokenManager;
import com.trade.auth.web.UserInfoDto;
import com.trade.exception.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component(value = "authServiceCustom")
public class AuthenticationServiceImpl implements AuthenticationService {
	private final PasswordEncoder passwordEncoder;
	private final UserRepo userRepo;
	private final AuthTokenManager tokenManager;

	@Autowired
	public AuthenticationServiceImpl(PasswordEncoder passwordEncoder,
	                                 UserRepo userRepo,
	                                 AuthTokenManager tokenManager) {
		this.passwordEncoder = passwordEncoder;
		this.userRepo = userRepo;
		this.tokenManager = tokenManager;
	}

	@Override
	@Transactional
	public AuthToken authenticate(AuthUserDto dto) {
		String phone = dto.getPhone();
		UserEntity user = userRepo.findFirstByPhone(phone);
		if (user == null) {
			throw new AuthException("Invalid credentials");
		}

		String password = dto.getPassword();
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new AuthException("Invalid credentials");
		}
		return tokenManager.generateToken(user.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public UserInfoDto authenticate(String token) {
		return Optional.ofNullable(tokenManager.get(token))
		               .map(AuthToken::getUserId)
		               .map(userRepo::findOne)
		               .map(UserInfoDto::new)
		               .orElseThrow(() -> new AuthException("Token expired or invalid"));
	}

	@Override
	@Transactional
	public void logout(String token) {
		if (token != null) {
			tokenManager.invalidate(token);
		}
		SecurityContextHolder.clearContext();
	}
}
