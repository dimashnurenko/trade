package com.trade.web.security.auth;

import com.trade.common.exception.InvalidCredentialsException;
import com.trade.common.exception.UserNotAuthenticatedException;
import com.trade.domain.user.User;
import com.trade.domain.user.UserRepo;
import com.trade.web.security.token.AuthToken;
import com.trade.web.security.token.AuthTokenDto;
import com.trade.web.security.token.AuthTokenMapper;
import com.trade.web.security.token.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;

import static com.trade.common.DateTimeUtil.currentDatePlusMinutes;
import static com.trade.web.security.SecurityUtils.createSecurityToken;
import static java.lang.String.format;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
	private static final int TOKEN_EXPIRATION_TIME_IN_MINUTES = 15;

	private final SecureRandom random = new SecureRandom();

	private final PasswordEncoder passwordEncoder;
	private final UserRepo userRepo;
	private final TokenRepo tokenRepo;
	private final AuthTokenMapper tokenMapper;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthenticationServiceImpl(PasswordEncoder passwordEncoder,
	                                 UserRepo userRepo,
	                                 TokenRepo tokenRepo,
	                                 AuthTokenMapper tokenMapper,
	                                 AuthenticationManager authenticationManager) {
		this.passwordEncoder = passwordEncoder;
		this.userRepo = userRepo;
		this.tokenRepo = tokenRepo;
		this.tokenMapper = tokenMapper;
		this.authenticationManager = authenticationManager;
	}

	@Override
	@Transactional
	public AuthTokenDto authenticate(AuthUserDto dto) {
		String phone = dto.getPhone();
		User user = userRepo.findFirstByPhone(phone);
		if (user == null) {
			throw new UserNotAuthenticatedException(format("The user with phone: %s doesn't exists", phone));
		}

		String password = dto.getPassword();
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new InvalidCredentialsException("The password is wrong.");
		}

		SecurityContextHolder.getContext().setAuthentication(createSecurityToken(user));

		Long userId = user.getId();
		return Optional.ofNullable(tokenRepo.findFirstByUserId(userId))
		               .map(tokenMapper::map)
		               .orElseGet(() -> createToken(userId));
	}

	@Override
	public void logout(Long userId) {
		tokenRepo.deleteByUserId(userId);

		SecurityContextHolder.clearContext();
	}

	private AuthTokenDto createToken(Long userId) {
		AuthToken authToken = new AuthToken();
		authToken.setUserId(userId);
		authToken.setToken(generateToken());
		authToken.setExpirationDate(currentDatePlusMinutes(TOKEN_EXPIRATION_TIME_IN_MINUTES));

		return tokenMapper.map(tokenRepo.save(authToken));
	}

	private synchronized String generateToken() {
		long longToken = Math.abs(random.nextLong());
		return Long.toString(longToken, 16);
	}
}
