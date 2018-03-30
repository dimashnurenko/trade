package com.trade.auth.user;

import com.trade.auth.user.model.User;
import com.trade.auth.user.model.UserDto;
import com.trade.auth.user.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(target = "password",
	         expression = "java(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(dto.getPassword()))")
	UserEntity toEntity(UserDto dto);

	User toModel(UserEntity entity);
}
