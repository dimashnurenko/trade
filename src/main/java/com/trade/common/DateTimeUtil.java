package com.trade.common;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	private static final long ONE_MINUTE_IN_MILLIS = 60000;

	public static Date currentDatePlusMinutes(int mins) {
		Calendar date = Calendar.getInstance();
		long currentTimeInMilis = date.getTimeInMillis();
		return new Date(currentTimeInMilis + (mins * ONE_MINUTE_IN_MILLIS));
	}
}
