package com.trade.domain.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
	User findFirstByPhone(String phone);
}
