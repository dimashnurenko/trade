package com.trade.parser;

import com.trade.common.exception.ServerException;

import java.net.MalformedURLException;
import java.net.URL;

import static com.trade.common.errorcode.ErrorCodes.INTERNAL_SERVER_ERROR;
import static com.trade.parser.WebContentHost.define;

class WebHostProvider {

	private final String link;

	WebHostProvider(String link) {
		this.link = link;
	}

	WebContentHost getHost() {
		try {
			URL url = new URL(link);
			return define(url.getHost());
		} catch (MalformedURLException e) {
			throw new ServerException(INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
