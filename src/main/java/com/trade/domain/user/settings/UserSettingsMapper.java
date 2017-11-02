package com.trade.domain.user.settings;

import com.trade.web.settings.UserSettingsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserSettingsMapper {
	UserSettings map(UserSettingsDto dto);
}
