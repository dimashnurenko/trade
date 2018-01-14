package com.trade.domain.product;

import com.trade.common.exception.ResourceNotFoundException;
import com.trade.domain.feed.FeedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	public Product createProduct(ProductDto dto, Long feedId) {
		if (feedRepo.exists(feedId)) {
			ProductEntity entity = productMapper.toEntity(dto);
			entity.setFeedId(feedId);
			return productMapper.toModel(productRepo.save(entity));
		}
		throw new ResourceNotFoundException(feedId, "feed");
	}

	@Override
	public List<Product> findAllByFeedId(Long feedId) {
		return null;
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
