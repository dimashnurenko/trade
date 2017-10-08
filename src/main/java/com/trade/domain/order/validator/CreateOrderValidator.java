package com.trade.domain.order.validator;

import com.trade.common.exception.ValidationException;
import com.trade.domain.user.UserRepo;
import com.trade.validator.Validator;
import com.trade.web.order.OrderDto;
import org.springframework.stereotype.Component;

import static com.trade.common.errorcode.ErrorCodes.ORDER_LINK_IS_EMPTY;
import static com.trade.common.errorcode.ErrorCodes.USER_NOT_EXISTS;

@Component
public class CreateOrderValidator implements Validator<OrderDto> {
	private final UserRepo userRepo;

	public CreateOrderValidator(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public void validate(OrderDto dto) {
		String link = dto.getLink();
		if (link == null || link.isEmpty()) {
			throw new ValidationException(ORDER_LINK_IS_EMPTY, "Link is required");
		}

		Long userId = dto.getUserId();
		if (userId == null || !userRepo.exists(userId)) {
			throw new ValidationException(USER_NOT_EXISTS, "User with such id doesn't exists");
		}
	}
}
