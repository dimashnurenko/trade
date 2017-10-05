package com.trade.validator;

public interface Validator<T> {
	void validate(T obj);
}
