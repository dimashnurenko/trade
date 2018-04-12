package com.trade.core.domain.product;

import com.google.common.eventbus.EventBus;
import com.trade.core.domain.feed.FeedEntity;
import com.trade.core.domain.feed.FeedRepo;
import com.trade.core.domain.follower.FollowersService;
import com.trade.exception.CoreAPIException;
import com.trade.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.trade.core.domain.events.product.ProductCreatedEvent.Builder.builder;
import static com.trade.exception.CoreExceptionReason.INCOMPATIBLE_DATA;
import static com.trade.exception.client.ApiExceptionDetails.exceptionDetails;
import static java.util.stream.Collectors.toList;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepo productRepo;
	private final ProductMapper productMapper;
	private final FeedRepo feedRepo;
	private final FollowersService followersService;
	private final EventBus eventBus;

	@Autowired
	public ProductServiceImpl(ProductRepo productRepo,
	                          ProductMapper productMapper,
	                          FeedRepo feedRepo,
	                          FollowersService followersService,
	                          EventBus eventBus) {
		this.productRepo = productRepo;
		this.productMapper = productMapper;
		this.feedRepo = feedRepo;
		this.followersService = followersService;
		this.eventBus = eventBus;
	}

	@Override
	@Transactional
	public Product createProduct(ProductDto dto, Long feedId, Long userId) {
		FeedEntity feedEntity = feedRepo.findOne(feedId);
		if (feedEntity == null) {
			throw new ResourceNotFoundException(exceptionDetails("resource.not.found",
			                                                     new Object[]{"feed", feedId}));
		}

		if (!userId.equals(feedEntity.getUserId())) {
			throw new CoreAPIException(INCOMPATIBLE_DATA, exceptionDetails("feed.not.belong.to.user", new Object[]{feedId, userId}));
		}

		ProductEntity entity = productMapper.toEntity(dto);
		entity.setFeedId(feedId);
		Product product = productMapper.toModel(productRepo.save(entity));

		eventBus.post(builder().productId(product.getId())
		                       .userId(userId)
		                       .build());
		return product;
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
		               .orElseThrow(() -> new ResourceNotFoundException(exceptionDetails("resource.not.found", new Object[]{"product", id})));
	}
}
