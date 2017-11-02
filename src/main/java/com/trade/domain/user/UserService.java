package com.trade.domain.user;

import com.trade.web.user.UserDto;


public interface UserService {

	User findOne(Long id);

	User createUser(UserDto user);

	User updateUser(UserDto user);
}
