package com.trade.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
	List<ProductEntity> findAllByFeedId(Long feedId);
}
