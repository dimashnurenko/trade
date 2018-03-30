package com.trade.auth.user;

import com.trade.auth.user.model.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepo extends CrudRepository<UserRole, Long> {
}
