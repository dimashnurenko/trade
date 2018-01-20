package com.trade.domain.product;

import com.trade.common.exception.ResourceNotFoundException;
import com.trade.common.exception.ServerException;
import com.trade.domain.feed.FeedEntity;
import com.trade.domain.feed.FeedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepo productRepo;
	private final ProductMapper productMapper;
	private final FeedRepo feedRepo;

	@Autowired
	public ProductServiceImpl(ProductRepo productRepo, ProductMapper productMapper, FeedRepo feedRepo) {
		this.productRepo = productRepo;
		this.productMapper = productMapper;
		this.feedRepo = feedRepo;
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
	public List<Product> findAllByFeedId(Long feedId) {
		List<ProductEntity> entities = productRepo.findAllByFeedId(feedId);
		return entities.stream().map(productMapper::toModel).collect(toList());
	}

	@Override
	public List<Product> findAllByFeedsIds(List<Long> feedsIds) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return null;
	}

	@Override
	@Transactional
	public void delete(Long id) {

	}
}
