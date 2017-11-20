package com.trade.web.group;

import org.springframework.hateoas.ResourceSupport;

public class GroupResource extends ResourceSupport {
	private Long groupId;
	private Long userId;
	private String name;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
