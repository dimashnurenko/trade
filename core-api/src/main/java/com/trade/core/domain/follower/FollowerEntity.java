package com.trade.core.domain.follower;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "followers")
public class FollowerEntity {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column(name = "user_id")
	private Long userId;
	@NotNull
	@Column(name = "followed_on")
	private Long followedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFollowedOn() {
		return followedOn;
	}

	public void setFollowedOn(Long followedOn) {
		this.followedOn = followedOn;
	}
}
