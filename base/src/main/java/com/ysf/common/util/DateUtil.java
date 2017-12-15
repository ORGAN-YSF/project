package com.ysf.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author sunwenxing
 * 时间工具类
 */
public class DateUtil {
	private static SimpleDateFormat yearMonthDayHourMinuteSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	private static SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
	
	public static String DateToStringLong(Date date) {
		String dateStr = yearMonthDayHourMinuteSecond.format(date);
		return dateStr;
	}
	
	public static String DateToString(Date date) {
		String dateStr = yearMonthDay.format(date);
		return dateStr;
	}
	
	public static Date StringLongToDate(String dateStr) throws ParseException {
		Date date = yearMonthDayHourMinuteSecond.parse(dateStr);
		return date;
	}
	
	public static Date StringToDate(String dateStr) throws ParseException {
		Date date = yearMonthDay.parse(dateStr);
		return date;
	}
}
