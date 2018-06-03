package com.trade.auth.user;

import com.trade.auth.user.model.Role;
import com.trade.auth.user.model.User;
import com.trade.auth.user.model.UserDto;
import com.trade.auth.user.model.UserEntity;
import com.trade.auth.user.model.UserRole;
import com.trade.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.trade.auth.user.model.Role.USER;
import static java.lang.String.format;
import static java.util.Collections.singletonList;

@Service class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

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
		return userRepo.findById(id)
		               .map(mapper::toModel)
		               .orElseThrow(() -> new ResourceNotFoundException("user", id));
	}

	@Override
	@Transactional
	public User createUser(UserDto dto) {
		UserEntity user = userRepo.save(mapper.toEntity(dto));

		UserRole role = createUserRole(USER, user);
		user.setRoles(singletonList(role));

		return mapper.toModel(user);
	}

	private UserRole createUserRole(Role userRole, UserEntity user) {
		UserRole role = new UserRole();
		role.setRole(userRole);
		role.setUser(user);

		return userRoleRepo.save(role);
	}

	@Override
	@Transactional
	public User updateUser(UserDto dto) {
		UserEntity user = userRepo.save(mapper.toEntity(dto));
		LOG.debug(format("User with id: %s updated.", user.getId()));
		return mapper.toModel(user);
	}
}
