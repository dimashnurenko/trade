package com.trade.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
	Page<ProductEntity> findAllByFeedIdInOrderByCreatedDate(List<Long> feedIds, Pageable pageable);
}
