package com.trade.parser;

import com.trade.common.exception.ServerException;
import org.springframework.stereotype.Component;

import static com.trade.common.errorcode.ErrorCodes.INTERNAL_SERVER_ERROR;
import static java.lang.String.format;

@Component
public class WebContentParserFactory {

	public WebContentParser createParser(String link) {
		WebContentHost host = new WebHostProvider(link).getHost();
		switch (host) {
			case GAP_UK:
				return new GapUkContentParser(link);
		}
		throw new ServerException(INTERNAL_SERVER_ERROR, format("Parser for host: %s not supported yet", host));
	}
}
