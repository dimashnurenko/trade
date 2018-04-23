package com.trade.core.domain.feed;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Component
public class FeedServiceImpl implements FeedService {

	private final FeedRepo feedRepo;

	public FeedServiceImpl(FeedRepo feedRepo) {
		this.feedRepo = feedRepo;
	}

	@Override
	public FeedEntity findOneByUserIdOrCreateNew(Long userId) {
		return Optional.ofNullable(feedRepo.findOneByUserId(userId))
		               .orElseGet(createNewFeed(userId));
	}

	@Override
	public List<FeedEntity> findAllByUserIdIn(List<Long> userIds) {
		return feedRepo.findAllByUserIdIn(userIds);
	}

	private Supplier<FeedEntity> createNewFeed(Long userId) {
		FeedEntity feed = new FeedEntity();
		feed.setUserId(userId);
		return () -> feedRepo.save(feed);
	}
}
