package com.trade.domain.feed;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepo extends JpaRepository<FeedEntity, Long> {
	FeedEntity findOneByUserId(Long userId);
}
