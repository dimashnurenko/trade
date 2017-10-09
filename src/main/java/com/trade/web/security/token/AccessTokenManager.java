package com.trade.web.security.token;

import com.google.common.cache.Cache;
import com.trade.common.exception.InvalidTokenException;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.google.common.cache.CacheBuilder.newBuilder;
import static java.util.concurrent.TimeUnit.MINUTES;

@Component
public class AccessTokenManager {
	private static final Cache<Long, AccessToken> inMemoryTokens = newBuilder().concurrencyLevel(4)
	                                                                           .expireAfterAccess(10, MINUTES)
	                                                                           .build();

	public void invalidate(Long userId) {
		inMemoryTokens.invalidate(userId);
	}

	AccessToken get(Long userId) throws InvalidTokenException {
		return inMemoryTokens.getIfPresent(userId);
	}


	public AccessToken generateToken(Long userId) {
		AccessToken token = createToken(userId);
		inMemoryTokens.put(userId, token);
		return token;
	}

	private AccessToken createToken(Long userId) {
		AccessToken authToken = new AccessToken();
		authToken.setUserId(userId);
		String token = userId + UUID.randomUUID().toString();
		authToken.setToken(token);

		inMemoryTokens.put(userId, authToken);

		return authToken;
	}
}
