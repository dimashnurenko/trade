package com.trade.domain.user.settings;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserSettingsRepo extends CrudRepository<UserSettings, Long> {
	List<UserSettings> findAllByUserId(Long buyerId);
}
