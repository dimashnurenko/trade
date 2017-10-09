package com.trade.domain.user;

import com.trade.web.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDto map(User user);

	@Mapping(target = "password",
	         expression = "java(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(dto.getPassword()))")
	User map(UserDto dto);
}
