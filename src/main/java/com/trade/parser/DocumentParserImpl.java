package com.trade.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Component class DocumentParserImpl implements DocumentParser {

	@Override
	public List<String> getAllLinks(String url) {
		try {
			Document doc = Jsoup.connect(url).get();

			Elements elements = doc.select("a");

			return elements.stream().map(it -> it.attr("href")).collect(toList());
		} catch (IOException e) {
			throw new IllegalStateException(format("Cant get content by url %s", url), e);
		}
	}
}
