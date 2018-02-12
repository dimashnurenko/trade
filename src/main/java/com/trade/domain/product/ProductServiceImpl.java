package com.trade.domain.product;

import com.trade.common.exception.ResourceNotFoundException;
import com.trade.common.exception.ServerException;
import com.trade.domain.feed.FeedEntity;
import com.trade.domain.feed.FeedRepo;
import com.trade.domain.follower.FollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepo productRepo;
	private final ProductMapper productMapper;
	private final FeedRepo feedRepo;
	private final FollowersService followersService;

	@Autowired
	public ProductServiceImpl(ProductRepo productRepo,
	                          ProductMapper productMapper,
	                          FeedRepo feedRepo,
	                          FollowersService followersService) {
		this.productRepo = productRepo;
		this.productMapper = productMapper;
		this.feedRepo = feedRepo;
		this.followersService = followersService;
	}

	@Override
	@Transactional
	public Product createProduct(ProductDto dto, Long feedId, Long userId) {
		FeedEntity feedEntity = feedRepo.findOne(feedId);
		if (feedEntity == null) {
			throw new ResourceNotFoundException(feedId, "Feed");
		}

		if (!userId.equals(feedEntity.getUserId())) {
			throw new ServerException(format("The feed %s doesn't belong to user %s", feedId, userId));
		}

		ProductEntity entity = productMapper.toEntity(dto);
		entity.setFeedId(feedId);
		return productMapper.toModel(productRepo.save(entity));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Product> findAllByUserId(Long userId, Pageable pageable) {
		List<Long> followedUsers = followersService.findFollowersIdsByUserId(userId);
		List<Long> userIds = new ArrayList<>();
		userIds.add(userId);
		userIds.addAll(followedUsers);

		List<Long> feeds = feedRepo.findAllByUserIdIn(userIds).stream().map(FeedEntity::getId).collect(toList());
		Page<ProductEntity> entities = productRepo.findAllByFeedIdInOrderByCreatedDate(feeds, pageable);
		return entities.map(productMapper::toModel);
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return Optional.ofNullable(productRepo.findOne(id))
		               .map(productMapper::toModel)
		               .orElseThrow(() -> new ResourceNotFoundException(id, "product"));
	}
}
