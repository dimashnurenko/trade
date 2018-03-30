package com.trade.core.domain.order.summary;

public interface SummaryService {
	OrderSummary findOneByOrderId(Long orderId);
}
