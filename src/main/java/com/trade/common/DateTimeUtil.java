package com.trade.common;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.time.ZoneId.systemDefault;

public class DateTimeUtil {
	private static final String UTC_ZONE_ID = "UTC";

	public static LocalDateTime toUtc(LocalDateTime dateTime) {
		ZonedDateTime ldtZoned = dateTime.atZone(systemDefault());
		return ldtZoned.withZoneSameInstant(of(UTC_ZONE_ID)).toLocalDateTime();
	}

	public static Date toUtcDate(LocalDateTime dateTime) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(dateTime.toInstant(ZoneOffset.UTC).toEpochMilli()));
		calendar.setTimeZone(TimeZone.getTimeZone(UTC_ZONE_ID));
		return calendar.getTime();
	}

	public static void main(String[] args) {
		System.out.println(toUtc(LocalDateTime.now()));
		System.out.println(toUtcDate(LocalDateTime.now()));
	}
}
