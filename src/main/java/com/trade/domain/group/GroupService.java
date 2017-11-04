package com.trade.domain.group;

import com.trade.web.group.GroupDto;

public interface GroupService {
	Group create(Long userId, GroupDto dto);
}
