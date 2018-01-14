package com.trade.domain.user.listeners;

public class UserCreatedEvent {
	private final Long userId;

	public UserCreatedEvent(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}
}
