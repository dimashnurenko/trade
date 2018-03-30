package com.trade.core.domain.order.recipient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepo extends JpaRepository<RecipientEntity, Long> {
	RecipientEntity findOneByOrderId(Long orderId);
}
