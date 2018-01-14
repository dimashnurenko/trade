package com.trade.domain.user;


public interface UserService {

	User findOne(Long id);

	User createUser(UserDto user);

	User updateUser(UserDto user);
}
