package com.trade.domain.order.summary;

public interface SummaryService {
	OrderSummary findOneByOrderId(Long orderId);
}
