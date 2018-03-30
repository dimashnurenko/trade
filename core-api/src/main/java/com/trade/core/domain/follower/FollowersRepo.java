package com.trade.core.domain.follower;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowersRepo extends JpaRepository<FollowerEntity, Long> {
	List<FollowerEntity> findAllByUserId(Long userId);
}
