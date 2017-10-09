package com.trade.web.security.auth;

import com.trade.common.exception.InvalidCredentialsException;
import com.trade.common.exception.UserNotAuthenticatedException;
import com.trade.domain.user.User;
import com.trade.domain.user.UserRepo;
import com.trade.web.security.token.AccessToken;
import com.trade.web.security.token.AccessTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.trade.web.security.SecurityUtils.createAuthentication;
import static java.lang.String.format;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
	private final PasswordEncoder passwordEncoder;
	private final UserRepo userRepo;
	private final AccessTokenManager tokenManager;

	@Autowired
	public AuthenticationServiceImpl(PasswordEncoder passwordEncoder,
	                                 UserRepo userRepo,
	                                 AccessTokenManager tokenManager) {
		this.passwordEncoder = passwordEncoder;
		this.userRepo = userRepo;
		this.tokenManager = tokenManager;
	}

	@Override
	@Transactional
	public AccessToken authenticate(AuthUserDto dto) {
		String phone = dto.getPhone();
		User user = userRepo.findFirstByPhone(phone);
		if (user == null) {
			throw new UserNotAuthenticatedException(format("The user with phone: %s doesn't exists", phone));
		}

		String password = dto.getPassword();
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new InvalidCredentialsException("The password is wrong.");
		}

		SecurityContextHolder.getContext().setAuthentication(createAuthentication(user));

		return tokenManager.generateToken(user.getId());
	}

	@Override
	public void logout(Long userId) {
		tokenManager.invalidate(userId);
		SecurityContextHolder.clearContext();
	}
}
