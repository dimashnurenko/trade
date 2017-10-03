package com.trade.search;

import com.trade.parser.DocumentParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndexService {
	private final ElasticSearchService searchService;
	private final DocumentParser documentParser;

	@Autowired
	public IndexService(ElasticSearchService searchService, DocumentParser documentParser) {
		this.searchService = searchService;
		this.documentParser = documentParser;
	}

	public List<String> getLinks(String url) {
		return documentParser.getAllLinks(url);
	}
}
