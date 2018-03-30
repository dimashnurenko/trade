package com.trade.auth.user;


import com.trade.auth.user.model.User;
import com.trade.auth.user.model.UserDto;

public interface UserService {

	User findOne(Long id);

	User createUser(UserDto user);

	User updateUser(UserDto user);
}
