package com.trade.core.domain.order.summary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryRepo extends JpaRepository<OrderSummaryEntity, Long> {
	OrderSummaryEntity findByOrderId(Long orderId);
}
