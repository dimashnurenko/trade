package com.trade.domain.user;

import com.trade.web.user.UserDto;


public interface UserService {

    UserDto findOne(Long id);

    long createUser(UserDto user);

    void updateUser(UserDto user);
}
