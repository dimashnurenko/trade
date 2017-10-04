package com.trade.order;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	OrderDto map(Order order);
}
