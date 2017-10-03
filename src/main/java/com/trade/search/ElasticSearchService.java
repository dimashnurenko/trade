package com.trade.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ElasticSearchService {

	private final RestTemplate restTemplate;
	private final String url;

	@Autowired
	public ElasticSearchService(@Qualifier("spring.elasticsearch.url") String url) {
		this.restTemplate = new RestTemplate();
		this.url = url;
	}
}
