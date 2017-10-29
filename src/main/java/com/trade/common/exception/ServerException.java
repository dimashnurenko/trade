package com.trade.common.exception;

public class ServerException extends RuntimeException {

	private final int code;

	public ServerException(int code, String message) {
		super(message);

		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
