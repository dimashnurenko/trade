package com.trade.domain.user.validator;


import com.trade.common.exception.ValidationException;
import com.trade.validator.Validator;
import com.trade.web.user.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.trade.common.errorcode.ErrorCodes.FIELD_REQUIRED;

@Component
public class CreateUserValidator implements Validator<UserDto> {
	@Override
	public void validate(UserDto dto) {
		if (StringUtils.isEmpty(dto.getPassword())) {
			throw new ValidationException(FIELD_REQUIRED, "Password is required");
		}

		if (StringUtils.isEmpty(dto.getPhone())) {
			throw new ValidationException(FIELD_REQUIRED, "User phone is required");
		}
	}
}
