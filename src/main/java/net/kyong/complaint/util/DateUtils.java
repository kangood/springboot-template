package net.kyong.complaint.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 */
public class DateUtils {

	/**
	 * 线程安全的实现
	 */
	private static final ThreadLocal<DateFormat> SDF_YYYYMMDDHHMMSS = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmss");
		}
	};
	private static final ThreadLocal<DateFormat> SDF_YYYYMMDD = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	private static final ThreadLocal<DateFormat> SDF_YYYYMMDD_HHMMSS = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	public static String dateFormatOfSerial(Date date) {
		return SDF_YYYYMMDDHHMMSS.get().format(date);
	}

	public static String dateFormatOfHyphen(Date date) {
		return SDF_YYYYMMDD_HHMMSS.get().format(date);
	}

	public static String dateFormatByDay(Date date) {
		return SDF_YYYYMMDD.get().format(date);
	}

	/**
	 * 加减对应天数，正数为增，复述为减
	 * @param dateTime
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	public static Date daysPlusOrMinus(Date dateTime, int days) throws ParseException {
		Calendar instance = Calendar.getInstance();
		instance.setTime(dateTime);
		instance.add(Calendar.DAY_OF_YEAR, days);
		return instance.getTime();
	}

}
