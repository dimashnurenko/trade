package com.trade.domain.order;

import com.trade.web.order.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	OrderDto map(Order order);

	Order map(OrderDto dto);
}
