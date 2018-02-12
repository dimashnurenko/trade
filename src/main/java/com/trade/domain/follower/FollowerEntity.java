package com.trade.domain.follower;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
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
}
