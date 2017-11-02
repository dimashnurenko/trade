package com.trade.domain.user;

import com.trade.common.exception.ResourceNotFoundException;
import com.trade.web.user.UserDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static java.util.Optional.ofNullable;

@Service(value = "userService") class UserServiceImpl implements UserService {
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

	private final UserRepo userRepo;
	private final UserRoleRepo userRoleRepo;
	private final UserMapper mapper;

	UserServiceImpl(UserRepo userRepo, UserRoleRepo userRoleRepo, UserMapper mapper) {
		this.userRepo = userRepo;
		this.userRoleRepo = userRoleRepo;
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly = true)
	public User findOne(Long id) {
		return ofNullable(id).map(userRepo::findOne)
		                     .orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Override
	@Transactional
	public User createUser(UserDto dto) {
		User user = userRepo.save(mapper.map(dto));

		UserRole role = new UserRole();
		role.setRole(dto.getRole());
		role.setUser(user);

		user.setRoles(singletonList(role));

		userRoleRepo.save(role);

		LOG.debug(format("User with id: %s and role: %s created.", user.getId(), dto.getRole()));
		return user;
	}

	@Override
	@Transactional
	public User updateUser(UserDto dto) {
		User user = userRepo.save(mapper.map(dto));
		LOG.debug(format("User with id: %s updated.", user.getId()));
		return user;
	}
}
