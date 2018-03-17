package com.trade.domain.user.listeners;

import com.google.common.eventbus.Subscribe;
import com.trade.domain.feed.FeedEntity;
import com.trade.domain.feed.FeedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserListener {
	private final FeedRepo feedRepo;

	@Autowired
	public UserListener(FeedRepo feedRepo) {
		this.feedRepo = feedRepo;
	}

	@Subscribe
	public void onUserCreated(UserCreatedEvent event) {
		FeedEntity feedEntity = new FeedEntity();
		feedEntity.setUserId(event.getUserId());

		feedRepo.save(feedEntity);
	}
}
