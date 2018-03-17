package com.trade.domain.order;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

	Order toModel(OrderEntity entity);
}
