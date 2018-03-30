package com.trade.core.domain.feed;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepo extends JpaRepository<FeedEntity, Long> {
	FeedEntity findOneByUserId(Long userId);

	List<FeedEntity> findAllByUserIdIn(List<Long> userIds);
}
