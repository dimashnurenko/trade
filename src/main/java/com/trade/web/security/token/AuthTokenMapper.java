package com.trade.web.security.token;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthTokenMapper {
	AuthTokenDto map(AuthToken token);
}
