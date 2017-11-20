package com.trade.web.user;

import com.trade.domain.user.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private String name;
	private String phone;
	private String password;
	private Role role;
}
