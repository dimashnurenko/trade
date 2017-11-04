package com.trade.domain.group;

import com.trade.web.group.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
	private final GroupRepo groupRepo;
	private final GroupMapper groupMapper;

	@Autowired
	public GroupServiceImpl(GroupRepo groupRepo, GroupMapper groupMapper) {
		this.groupRepo = groupRepo;
		this.groupMapper = groupMapper;
	}

	@Override
	public Group create(Long userId, GroupDto dto) {
		Group group = groupMapper.map(dto);
		group.setUserId(userId);
		return groupRepo.save(group);
	}
}
