package com.trade.parser;

import com.trade.common.exception.ServerException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;

import static com.trade.common.errorcode.ErrorCodes.INTERNAL_SERVER_ERROR;
import static com.trade.common.errorcode.ErrorCodes.PARSER_ERROR;
import static java.lang.String.format;

class GapUkContentParser implements WebContentParser {

	private final Document document;
	private final String host;

	GapUkContentParser(String link) {
		try {
			this.host = new URL(link).getHost();
			this.document = Jsoup.connect(link).get();
		} catch (IOException e) {
			throw new ServerException(INTERNAL_SERVER_ERROR, format("The connection refused. The url: %s", link));
		}
	}

	@Override
	public String getImageUrl() {
		return Optional.ofNullable(document.getElementsByClass("product-photo--container"))
		               .map(it -> it.select("img"))
		               .map(it -> it.attr("src"))
		               .map(it -> host + it)
		               .orElseThrow(() -> new ServerException(PARSER_ERROR, "Image url not found."));
	}

	@Override
	public String getTitle() {
		return Optional.ofNullable(document.getElementsByClass("product-title"))
		               .map(Elements::text)
		               .orElseThrow(() -> new ServerException(PARSER_ERROR, "Title not found"));
	}

	@Override
	public BigDecimal getPrice() {
		return new BigDecimal(Optional.ofNullable(document.getElementsByClass("product-price"))
		                              .map(Elements::text)
		                              .map(it -> it.substring(1))
		                              .orElseThrow(() -> new ServerException(PARSER_ERROR, "The price not found")));
	}
}
