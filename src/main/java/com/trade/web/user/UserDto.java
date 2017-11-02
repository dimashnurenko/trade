package com.trade.web.user;

import com.trade.domain.user.Role;
import lombok.Data;

@Data
public class UserDto {
	private String name;
	private String phone;
	private String password;
	private Role role;
}
