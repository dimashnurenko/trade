package com.trade.domain.product;

import java.util.List;

public interface ProductService {

	Product createProduct(ProductDto dto, Long feedId, Long userId);

	List<Product> findAllByFeedId(Long feedId);

	List<Product> findAllByFeedsIds(List<Long> feedsIds);

	Product findById(Long id);

	void delete(Long id);
}
