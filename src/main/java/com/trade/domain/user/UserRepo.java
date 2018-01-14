package com.trade.domain.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
	UserEntity findFirstByPhone(String phone);
}
