package com.trade.domain.group;

import com.trade.web.group.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Group create(Long userId, GroupDto dto) {
		Group group = groupMapper.map(dto);
		group.setCreatorId(userId);
		return groupRepo.save(group);
	}
}
