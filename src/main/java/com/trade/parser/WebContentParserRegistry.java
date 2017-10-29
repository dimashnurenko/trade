package com.trade.parser;

import com.trade.common.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.trade.common.errorcode.ErrorCodes.PARSER_NOT_SUPPORTED;
import static com.trade.parser.WebContentHost.GAP_UK;
import static java.lang.String.format;

@Component
public class WebContentParserRegistry {
	private final Map<WebContentHost, WebContentParser> parsers = new ConcurrentHashMap<>();

	@Autowired
	public WebContentParserRegistry(GapUkContentParser gapUkParser) {
		parsers.put(GAP_UK, gapUkParser);
	}

	public WebContentParser getContentParser(String link) {
		WebContentHost host = new WebHostProvider(link).getHost();

		return Optional.ofNullable(parsers.get(host))
		               .orElseThrow(() -> new ServerException(PARSER_NOT_SUPPORTED, format("Parser not supported for host: %s", host)));
	}
}
