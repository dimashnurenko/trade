package com.trade.auth.user;

import com.trade.auth.user.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
	UserEntity findFirstByPhone(String phone);
}
