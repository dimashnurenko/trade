package com.trade.domain.group;

import com.trade.web.group.GroupDto;
import com.trade.web.group.GroupResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {
	Group map(GroupDto dto);

	@Mapping(source = "id", target = "groupId")
	GroupResource map(Group group);
}
