package com.trade.domain.user;

import com.trade.validator.Validator;
import com.trade.web.user.UserDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "userServiceWrapper")
public class UserServiceWrapper implements UserService {

	private final UserService service;
	private final Validator<UserDto> userValidator;

	public UserServiceWrapper(@Qualifier("userService") UserService service,
	                          @Qualifier("createUserValidator") Validator<UserDto> userValidator) {
		this.service = service;
		this.userValidator = userValidator;
	}

	@Override
	public User findOne(Long id) {
		return service.findOne(id);
	}

	@Override
	public User createUser(UserDto user) {
		userValidator.validate(user);
		return service.createUser(user);
	}

	@Override
	public User updateUser(UserDto user) {
		userValidator.validate(user);
		return service.updateUser(user);
	}
}
