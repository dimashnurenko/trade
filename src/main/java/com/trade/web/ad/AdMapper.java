package com.trade.web.ad;

import com.trade.domain.ad.AdEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdMapper {
	AddResource map(AdEntity entity);
}
