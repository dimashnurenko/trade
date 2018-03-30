package com.trade.core.domain.follower;

import java.util.List;

public interface FollowersService {
	void follow(Long userId, Long followedOn);

	void unFollow(Long userId, Long followedOn);

	List<Long> findFollowersIdsByUserId(Long userId);
}
