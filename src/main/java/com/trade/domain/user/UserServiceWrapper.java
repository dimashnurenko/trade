package com.trade.domain.user;

import com.trade.validator.Validator;
import com.trade.web.user.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

@Service(value = "userServiceWrapper")
public class UserServiceWrapper implements UserService {

    private final UserService service;
    private final Validator<UserDto> userValidator;

    public UserServiceWrapper(@Qualifier("userService")UserService service,
                              @Qualifier("createUserValidator") Validator<UserDto> userValidator ){
        this.service = service;
        this.userValidator = userValidator;
    }


    @Override
    public UserDto findOne(Long id) {
        return service.findOne(id);
    }

    @Override
    public long createUser(UserDto user) {
        userValidator.validate(user);
        return service.createUser(user);
    }

    @Override
    public void updateUser(UserDto user) {
       userValidator.validate(user);
       service.updateUser(user);
    }
}
