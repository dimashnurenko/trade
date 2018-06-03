package com.trade.core.domain.follower;

import com.trade.exception.CoreAPIException;
import com.trade.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.trade.exception.CoreExceptionReason.INCOMPATIBLE_DATA;
import static com.trade.exception.client.ApiExceptionDetails.exceptionDetails;
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
		FollowerEntity follower = followersRepo.findById(followedOn)
		                                       .orElseThrow(() -> new ResourceNotFoundException(exceptionDetails("resource.not.found",
		                                                                                                         new Object[]{"follower", followedOn})));
		if (userId.equals(follower.getUserId())) {
			followersRepo.delete(follower);
		}
		throw new CoreAPIException(INCOMPATIBLE_DATA, exceptionDetails("follower.incompatible.with.user",
		                                                               new Object[]{follower.getId(), userId}));
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
