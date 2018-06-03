package com.trade.core.domain.user;


import com.trade.core.domain.user.model.User;
import com.trade.core.domain.user.model.UserDto;

public interface UserService {

	User findOne(Long id);

	User createUser(UserDto user);

	User updateUser(UserDto user);
}
