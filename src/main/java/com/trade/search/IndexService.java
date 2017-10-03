package com.trade.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndexService {
	private final ElasticSearchService searchService;

	@Autowired
	public IndexService(ElasticSearchService searchService) {
		this.searchService = searchService;
	}
}
