package com.trade.domain.user;

import com.trade.common.exception.ResourceNotFoundException;
import com.trade.domain.feed.FeedEntity;
import com.trade.domain.feed.FeedRepo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.lang.String.format;
import static java.util.Collections.singletonList;

@Service class UserServiceImpl implements UserService {
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

	private final UserRepo userRepo;
	private final UserRoleRepo userRoleRepo;
	private final UserMapper mapper;
	private final FeedRepo feedRepo;

	UserServiceImpl(UserRepo userRepo, UserRoleRepo userRoleRepo, UserMapper mapper, FeedRepo feedRepo) {
		this.userRepo = userRepo;
		this.userRoleRepo = userRoleRepo;
		this.mapper = mapper;
		this.feedRepo = feedRepo;
	}

	@Override
	@Transactional(readOnly = true)
	public User findOne(Long id) {
		return Optional.ofNullable(userRepo.findOne(id))
		               .map(this::mapToModel)
		               .orElseThrow(() -> new ResourceNotFoundException(id, "user"));
	}

	private User mapToModel(UserEntity entity) {
		FeedEntity feedEntity = Optional.ofNullable(feedRepo.findOneByUserId(entity.getId()))
		                                .orElseThrow(() -> new ResourceNotFoundException(format("Feeds for user %s not found.", entity.getId())));
		User model = mapper.toModel(entity);
		model.setFeedId(feedEntity.getId());
		return model;
	}

	@Override
	@Transactional
	public User createUser(UserDto dto) {
		UserEntity user = userRepo.save(mapper.toEntity(dto));

		UserRole role = createUserRole(dto.getRole());
		user.setRoles(singletonList(role));

		User model = mapper.toModel(user);

		FeedEntity feed = createFeed(user.getId());
		model.setFeedId(feed.getId());

		return model;
	}

	private UserRole createUserRole(Role userRole) {
		UserRole role = new UserRole();
		role.setRole(userRole);

		return userRoleRepo.save(role);
	}

	private FeedEntity createFeed(Long userId) {
		FeedEntity feed = new FeedEntity();
		feed.setUserId(userId);

		return feedRepo.save(feed);
	}

	@Override
	@Transactional
	public User updateUser(UserDto dto) {
		UserEntity user = userRepo.save(mapper.toEntity(dto));
		LOG.debug(format("User with id: %s updated.", user.getId()));
		return mapper.toModel(user);
	}
}
