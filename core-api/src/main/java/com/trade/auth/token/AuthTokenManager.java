package com.trade.auth.token;

import com.google.common.cache.Cache;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.google.common.cache.CacheBuilder.newBuilder;
import static java.time.LocalDateTime.now;

@Component
public class AuthTokenManager {
	private static final Cache<String, AuthToken> inMemoryTokens = newBuilder().concurrencyLevel(4)
	                                                                           .expireAfterAccess(60, TimeUnit.MINUTES)
	                                                                           .build();
	private final AuthTokenRepo tokenRepo;

	public AuthTokenManager(AuthTokenRepo tokenRepo) {
		this.tokenRepo = tokenRepo;
	}

	public void invalidate(String token) {
		inMemoryTokens.invalidate(token);
	}

	public AuthToken get(String token) {
		AuthToken inMemoryToken = inMemoryTokens.getIfPresent(token);
		AuthToken authToken = inMemoryToken == null ? tokenRepo.findOneByToken(token) : inMemoryToken;

		boolean expired = /*LocalDateTime.now().isAfter(authToken.getExpirationDate());*/false;
		if (expired) {
			invalidate(token);
		}

		return expired ? null : authToken;
	}

	@Transactional
	public AuthToken generateToken(Long userId) {
		tokenRepo.removeAllByUserId(userId);

		AuthToken token = createToken(userId);
		inMemoryTokens.put(token.getToken(), token);
		tokenRepo.save(token);
		return token;
	}

	private AuthToken createToken(Long userId) {
		AuthToken authToken = new AuthToken();
		String token = UUID.randomUUID().toString() + '-' + UUID.randomUUID().toString();
		authToken.setToken(token);
		authToken.setExpirationDate(now().plus(60, ChronoUnit.MINUTES));
		authToken.setUserId(userId);

		inMemoryTokens.put(token, authToken);

		return authToken;
	}
}
