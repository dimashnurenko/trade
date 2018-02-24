package com.trade.domain.order.summary;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SummaryMapper {

	OrderSummary toModel(OrderSummaryEntity entity);
}
