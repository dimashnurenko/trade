package com.trade.core.domain.feed;

import java.util.List;

public interface FeedService {

	FeedEntity findOneByUserIdOrCreateNew(Long userId);

	List<FeedEntity> findAllByUserIdIn(List<Long> userIds);
}
