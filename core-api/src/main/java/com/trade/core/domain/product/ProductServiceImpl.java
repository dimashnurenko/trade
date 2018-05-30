package com.trade.core.domain.product;

import com.google.common.eventbus.EventBus;
import com.trade.core.domain.feed.FeedEntity;
import com.trade.core.domain.feed.FeedService;
import com.trade.core.domain.follower.FollowersService;
import com.trade.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.trade.core.domain.events.product.ProductCreatedEvent.Builder.builder;
import static com.trade.exception.client.ApiExceptionDetails.exceptionDetails;
import static java.util.stream.Collectors.toList;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepo productRepo;
	private final ProductMapper productMapper;
	private final FeedService feedService;
	private final FollowersService followersService;
	private final ProductDataProvider productDataProvider;
	private final EventBus eventBus;

	public ProductServiceImpl(ProductRepo productRepo,
	                          ProductMapper productMapper,
	                          FeedService feedService,
	                          FollowersService followersService,
	                          ProductDataProvider productDataProvider,
	                          EventBus eventBus) {
		this.productRepo = productRepo;
		this.productMapper = productMapper;
		this.feedService = feedService;
		this.followersService = followersService;
		this.productDataProvider = productDataProvider;
		this.eventBus = eventBus;
	}

	@Override
	@Transactional
	public Product createProduct(ProductDto dto, Long userId) {
		FeedEntity feedEntity = feedService.findOneByUserIdOrCreateNew(userId);

		ProductEntity entity = productMapper.toEntity(dto);
		entity.setFeedId(feedEntity.getId());
		Product product = productMapper.toModel(productRepo.save(entity));

		eventBus.post(builder().productId(product.getId())
		                       .userId(userId)
		                       .build());
		return product;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Product> findAllByUserId(Long userId, Pageable pageable) {
		List<Long> followersIds = followersService.findFollowersIdsByUserId(userId);

		List<Long> followersWithUser = new ArrayList<>(followersIds);
		followersWithUser.add(userId);

		List<Long> feeds = feedService.findAllByUserIdIn(followersWithUser)
		                              .stream()
		                              .map(FeedEntity::getId)
		                              .collect(toList());
		Page<ProductEntity> entities = productRepo.findAllByFeedIdInOrderByCreatedDate(feeds, pageable);
		return entities.map(this::toProductModel);
	}

	private Product toProductModel(ProductEntity entity) {
		Product product = productMapper.toModel(entity);
		product.setDefaultImageId(productDataProvider.mainImageId(entity.getId()));
		return product;
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return Optional.ofNullable(productRepo.findOne(id))
		               .map(productMapper::toModel)
		               .orElseThrow(() -> new ResourceNotFoundException(exceptionDetails("resource.not.found", new Object[]{"product", id})));
	}
}
