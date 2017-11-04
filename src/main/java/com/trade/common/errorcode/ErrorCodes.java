package com.trade.common.errorcode;

public interface ErrorCodes {
	int FIELD_REQUIRED = 1_000;

	int PARSER_NOT_SUPPORTED = 2_000;
	int HOST_NOT_SUPPORTED = 2_001;
	int PARSER_ERROR = 2_002;

	int INTERNAL_SERVER_ERROR = 3_000;
}
