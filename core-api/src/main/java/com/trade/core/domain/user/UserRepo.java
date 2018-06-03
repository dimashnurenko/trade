package com.trade.core.domain.user;

import com.trade.core.domain.user.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
	UserEntity findFirstByPhone(String phone);
}
