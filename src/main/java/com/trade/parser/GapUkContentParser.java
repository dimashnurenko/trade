package com.trade.parser;

import com.trade.common.exception.ServerException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

import static com.trade.common.errorcode.ErrorCodes.INTERNAL_SERVER_ERROR;
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
		String imageSrc = document.getElementsByClass("product-photo--container")
		                          .select("img")
		                          .get(1)
		                          .attr("src");
		return host + '/' + imageSrc;
	}

	@Override
	public String getTitle() {
		return document.getElementsByClass("product-title").text();
	}

	@Override
	public BigDecimal getPrice() {
		return new BigDecimal(document.getElementsByClass("product-price")
		                              .text()
		                              .substring(1));
	}
}
