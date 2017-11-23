package com.trade.domain.group;

import com.trade.web.group.GroupDto;
import org.springframework.security.access.annotation.Secured;

public interface GroupService {
	@Secured({"ROLE_BUYER", "ROLE_ADMIN"})
	Group create(Long userId, GroupDto dto);
}
