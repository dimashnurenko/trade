package com.trade.domain.follower;

import com.trade.common.exception.ServerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Component
public class FollowersServiceImpl implements FollowersService {
	private final FollowersRepo followersRepo;

	public FollowersServiceImpl(FollowersRepo followersRepo) {
		this.followersRepo = followersRepo;
	}

	@Override
	@Transactional
	public void follow(Long userId, Long followedOn) {
		FollowerEntity follower = new FollowerEntity();
		follower.setUserId(userId);
		follower.setFollowedOn(followedOn);

		followersRepo.save(follower);
	}

	@Override
	@Transactional
	public void unFollow(Long userId, Long followedOn) {
		FollowerEntity follower = followersRepo.findOne(followedOn);
		if (userId.equals(follower.getUserId())) {
			followersRepo.delete(follower);
		}
		throw new ServerException(format("Follower id doesn't become to user: %s", userId));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Long> findFollowersIdsByUserId(Long userId) {
		return followersRepo.findAllByUserId(userId)
		                    .stream()
		                    .map(FollowerEntity::getFollowedOn)
		                    .collect(toList());
	}
}
