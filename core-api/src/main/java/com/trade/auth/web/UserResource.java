package com.trade.auth.web;

import com.trade.auth.user.model.Role;
import com.trade.auth.user.model.User;
import com.trade.auth.user.model.UserRole;
import com.trade.auth.web.UsersController;
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

	public UserResource(User user) {
		this.userId = user.getId();
		this.name = user.getName();
		this.phone = user.getPhone();
		this.roles = user.getRoles().stream().map(UserRole::getRole).collect(toList());

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
}
