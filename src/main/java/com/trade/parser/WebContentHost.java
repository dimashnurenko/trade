package com.trade.parser;

import com.trade.common.exception.ServerException;

import java.util.stream.Stream;

import static com.trade.common.errorcode.ErrorCodes.HOST_NOT_SUPPORTED;
import static java.lang.String.format;

public enum WebContentHost {
	GAP_UK("www.gap.co.uk");

	private final String host;

	WebContentHost(String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public static WebContentHost define(String host) {
		return Stream.of(values())
		             .filter(it -> it.getHost().equals(host))
		             .findFirst()
		             .orElseThrow(() -> new ServerException(HOST_NOT_SUPPORTED, format("Web content parser isn't defined for host: %s", host)));
	}
}
