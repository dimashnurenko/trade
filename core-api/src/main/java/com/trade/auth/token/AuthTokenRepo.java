package com.trade.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthTokenRepo extends JpaRepository<AuthToken, Long> {
	AuthToken findOneByToken(String token);

	void removeAllByUserId(Long userId);
}
