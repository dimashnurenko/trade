package com.trade.core.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepo extends JpaRepository<ProductImage, Long> {
	List<ProductImage> findAllByProductId(Long productId);
}
