package com.trade.core.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

	Product createProduct(ProductDto dto, Long userId);

	Page<Product> findAllByUserId(Long userId, Pageable pageable);

	List<Product> findAllByTagName(String tagName, Long userId);

	Product findById(Long id);
}
