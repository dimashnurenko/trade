package com.trade.web.security.token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<AuthToken, Long> {
	AuthToken findFirstByUserId(Long userId);

	void deleteByUserId(Long userId);
}
