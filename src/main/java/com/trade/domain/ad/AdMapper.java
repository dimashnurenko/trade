package com.trade.domain.ad;

import com.trade.web.ad.AddResource;
import com.trade.ws.AdMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdMapper {
	@Mapping(source = "id", target = "addId")
	AddResource map(Ad entity);

	AdMessage mapToMessage(Ad entity);
}
