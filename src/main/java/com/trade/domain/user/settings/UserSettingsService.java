package com.trade.domain.user.settings;

import com.trade.web.settings.UserSettingsDto;

public interface UserSettingsService {

	UserSettings create(Long userId, UserSettingsDto settingsDto);
}
