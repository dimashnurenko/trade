package com.trade.web.user;

import com.trade.domain.user.Role;
import com.trade.domain.user.User;
import com.trade.domain.user.UserRole;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


public class UserResource extends ResourceSupport {
	private Long userId;
	private String name;
	private String phone;
	private List<Role> roles;
	private Long feedId;

	public UserResource(User user) {
		this.userId = user.getId();
		this.name = user.getName();
		this.phone = user.getPhone();
		this.roles = user.getRoles().stream().map(UserRole::getRole).collect(toList());
		this.feedId = user.getFeedId();

		add(linkTo(methodOn(UsersController.class).findOne(userId)).withSelfRel());
	}

	public Long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public Long getFeedId() {
		return feedId;
	}
}
