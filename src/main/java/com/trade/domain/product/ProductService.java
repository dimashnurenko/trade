package com.trade.domain.product;

import java.util.List;

public interface ProductService {

	Product createProduct(ProductDto dto, Long feedId, Long userId);

	List<Product> findAllByUserId(Long userId);

	Product findById(Long id);
}
