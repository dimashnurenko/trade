package com.trade.domain.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepo extends CrudRepository<UserRole, Long> {
	List<UserRole> findAllByUserId(Long userId);
}
