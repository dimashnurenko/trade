package com.trade.domain.user;

import com.trade.web.user.UsersController;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Getter
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
}
