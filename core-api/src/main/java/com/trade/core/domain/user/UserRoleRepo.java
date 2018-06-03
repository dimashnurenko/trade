package com.trade.core.domain.user;

import com.trade.core.domain.user.model.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepo extends CrudRepository<UserRole, Long> {
}
