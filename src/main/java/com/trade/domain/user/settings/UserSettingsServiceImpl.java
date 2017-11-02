package com.trade.domain.user.settings;

import com.trade.web.settings.UserSettingsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserSettingsServiceImpl implements UserSettingsService {
	private final UserSettingsRepo settingsRepo;
	private final UserSettingsMapper settingsMapper;

	@Autowired
	public UserSettingsServiceImpl(UserSettingsRepo settingsRepo, UserSettingsMapper settingsMapper) {
		this.settingsRepo = settingsRepo;
		this.settingsMapper = settingsMapper;
	}

	@Override
	@Transactional
	public UserSettings create(Long userId, UserSettingsDto settingsDto) {
		UserSettings settings = settingsMapper.map(settingsDto);
		settings.setUserId(userId);
		return settingsRepo.save(settings);
	}
}
