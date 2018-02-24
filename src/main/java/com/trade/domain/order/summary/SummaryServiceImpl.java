package com.trade.domain.order.summary;

import org.springframework.stereotype.Component;

@Component
public class SummaryServiceImpl implements SummaryService {
	private final SummaryRepo summaryRepo;
	private final SummaryMapper mapper;

	public SummaryServiceImpl(SummaryRepo summaryRepo,
	                          SummaryMapper mapper) {
		this.summaryRepo = summaryRepo;
		this.mapper = mapper;
	}

	@Override
	public OrderSummary findOneByOrderId(Long orderId) {
		OrderSummaryEntity summaryEntity = summaryRepo.findByOrderId(orderId);
		return mapper.toModel(summaryEntity);
	}
}
