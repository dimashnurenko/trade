package com.trade.domain.user.validator;


import com.trade.common.ValidationException;

import com.trade.validator.Validator;
import com.trade.web.user.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.trade.common.errorcode.ErrorCodes.USER_NAME_IS_EMPTY;

import static com.trade.common.errorcode.ErrorCodes.USER_PHONE_IS_EMPTY;

@Component
public class CreateUserValidator implements Validator<UserDto> {
    @Override
    public void validate(UserDto dto) {
        if (StringUtils.isEmpty(dto.getName())) {
            throw new ValidationException(USER_NAME_IS_EMPTY, "User name is required");
        }

        if (StringUtils.isEmpty(dto.getPhone())){
            throw  new ValidationException(USER_PHONE_IS_EMPTY, "User phone is required");
        }
    }
}
