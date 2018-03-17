package com.trade.web.user;

public class LoggedUser {
	private final Long id;

	public LoggedUser(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
