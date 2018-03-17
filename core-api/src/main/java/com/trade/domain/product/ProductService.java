package com.trade.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

	Product createProduct(ProductDto dto, Long feedId, Long userId);

	Page<Product> findAllByUserId(Long userId, Pageable pageable);

	Product findById(Long id);
}