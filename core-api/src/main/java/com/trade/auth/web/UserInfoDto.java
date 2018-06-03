package com.trade.auth.web;

import com.trade.core.domain.user.model.UserEntity;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserInfoDto {
	private final String phone;
	private final List<String> roles;

	public UserInfoDto(UserEntity user) {
		this.phone = user.getPhone();
		this.roles = user.getRoles().stream().map(it -> "ROLE_" + it.getRole().name()).collect(toList());
	}

	public String getPhone() {
		return phone;
	}

	public List<String> getRoles() {
		return roles;
	}
}
