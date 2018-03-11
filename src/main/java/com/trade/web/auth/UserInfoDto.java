package com.trade.web.auth;

import com.trade.domain.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
public class UserInfoDto {
	private String phone;
	private List<String> roles;

	public UserInfoDto(UserEntity user) {
		this.phone = user.getPhone();
		this.roles = user.getRoles().stream().map(it -> "ROLE_" + it.getRole().name()).collect(toList());
	}
}
