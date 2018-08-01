package com.xiaohe.qd.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
public class DateUtil {
	public static int getCurWeek(Date begin, Date end) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(end);
			cal.setTime(begin);
			int week = cal2.get(Calendar.WEEK_OF_YEAR);
			int week_init = cal.get(Calendar.WEEK_OF_YEAR);
			if (cal2.get(Calendar.YEAR) - cal.get(Calendar.YEAR) > 0) {
				Date yearBegin = fmt.parse(cal2.get(Calendar.YEAR) + "-01-01");
				int weekend = cal2.get(Calendar.DAY_OF_WEEK);
				cal2.setTime(yearBegin);
				cal2.add(Calendar.DATE, -7);
				week = cal2.get(Calendar.WEEK_OF_YEAR) + week;
			}
			return week - week_init;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static Date getDateFromString(String dateStr) {
		return getDateFromString(dateStr, null);
	}

	public static Date getDateFromString(String dateStr, String pattern) {
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyy-MM-dd HH:mm";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getStrFromDate(Date date, String pattern) {
		java.text.DateFormat df = new java.text.SimpleDateFormat(pattern);
		String s = df.format(date);
		return s;
	}

	public static String getLongStrFromDate(Date date) {
		return getStrFromDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getDateStrFromDate(Date date) {
		return getStrFromDate(date, "yyyy-MM-dd");
	}

	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**
	 * ��ʾ����
	 * 
	 * @param date
	 * @return
	 */
	public static String showDate(Date date) {
		return parseDateTime(date, "yyyy-MM-dd");
	}

	/**
	 * ��ʾ���ں�ʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static String showDateTime(Date date) {
		return parseDateTime(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * ��ʽ������
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String parseDateTime(Date date, String pattern) {
		if (date == null)
			return "";
		return new SimpleDateFormat(pattern).format(date);
	}

	public static String getNowTime() {
		Date date = new Date();
		return showDateTime(date);
	}

	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public static java.sql.Date getJavaSqlDate() {
		return new java.sql.Date(new Date().getTime());
	}

	public static java.sql.Date getBirthFromIdentityCard(String identityCard) {
		if (identityCard == null) {
			return null;
		}
		int length = identityCard.length();

		if (length == 15) {
			identityCard = identityCard.substring(0, 6) + "19"
					+ identityCard.substring(6) + "x";

		}

		if (identityCard.length() == 18) {
			return java.sql.Date.valueOf(identityCard.substring(6, 10) + "-"
					+ identityCard.substring(10, 12) + "-"
					+ identityCard.substring(12, 14));
		} else {
			return null;
		}

	}

	/**
	 * 通过给定时间得到这段时间星期1到星期天之间具体日期
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static Date[] getWeekBetween(Date date) throws ParseException {
		long time = date.getTime();
		int day = date.getDay();
		long startTime = 0, endTime = 0;
		int start = 0, end = 0;
		Date startDate = null, endDate = null;
		Date[] dates = new Date[2];
		switch (day) {
		case 0:
			start -= 6;
			end = 0;
			break;
		case 1:
			start = 0;
			end += 6;
			break;
		case 2:
			start -= 1;
			end += 5;
			break;
		case 3:
			start -= 2;
			end += 4;
			break;
		case 4:
			start -= 3;
			end += 3;
			break;
		case 5:
			start -= 4;
			end += 2;
			break;
		case 6:
			start -= 5;
			end += 1;
			break;
		default:
			break;
		}
		startTime = time / 1000 + start * (60 * 60 * 24);
		endTime = time / 1000 + end * (60 * 60 * 24);
		startDate = new Date(startTime * 1000);
		endDate = new Date(endTime * 1000);
		dates[0] = startDate;
		dates[1] = endDate;
		return dates;
	}

	/**
	 * 根据给定时间得到这个月头和尾的具体时间
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static Date[] getMonthBetween(Date date) throws ParseException {

		Date firstDate = null, lastDate = null;
		Date[] dates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, date.getYear() + 1900);
		calendar.set(Calendar.MONTH, date.getMonth());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DATE, 1);
		firstDate = calendar.getTime();
		calendar.set(Calendar.DATE, maxDay--);
		lastDate = calendar.getTime();
		dates[0] = firstDate;
		dates[1] = lastDate;
		return dates;
	}

	/**
	 * 根据给定时间得到0点到24点时间
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static Date[] getDayBetween(Date date) throws ParseException {

		Date firstDate = null, lastDate = null;
		Date[] dates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, date.getYear() + 1900);
		calendar.set(Calendar.MONTH, date.getMonth());
		calendar.set(Calendar.DATE, date.getDate());
		calendar.set(Calendar.HOUR, -12);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		firstDate = calendar.getTime();
		calendar.set(Calendar.HOUR, 24);
		lastDate = calendar.getTime();
		dates[0] = firstDate;
		dates[1] = lastDate;
		return dates;
	}

	/**
	 * 得出一个月具体时间
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static List<Date> getMonthDay(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, date.getYear() + 1900);
		calendar.set(Calendar.MONTH, date.getMonth());
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < maxDay; i++) {
			calendar.set(Calendar.DATE, i + 1);
			list.add(calendar.getTime());
		}
		return list;
	}

	/**
	 * 通过年月日比较2日期是否相等
	 * 
	 * @return
	 */
	public static boolean compareEqualByYMD(Date first, Date last) {
		return first.compareTo(last) == 0;
	}

	/**
	 * 将数字星期转化为中文
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static String getDay2Chinese(Date date) throws ParseException {
		int day = date.getDay();

		String rtn = "";

		switch (day) {
		case 0:
			rtn = "日";
			break;
		case 1:
			rtn = "一";
			break;
		case 2:
			rtn = "二";
			break;
		case 3:
			rtn = "三";
			break;
		case 4:
			rtn = "四";
			break;
		case 5:
			rtn = "五";
			break;
		case 6:
			rtn = "六";
			break;
		default:
			break;
		}

		return rtn;
	}

	/**
	 * 当前时间向前或者后改变几天
	 * 
	 * @param time
	 *            时间
	 * @param format
	 *            时间格式
	 * @param days
	 *            改变的时间天数
	 * @return
	 * @throws Exception
	 */
	public static String changeTime(Date time, String format, int days)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.getDateFromString(DateUtil.getStrFromDate(
				null == time ? new Date() : time, format), format));
		calendar.add(calendar.DATE, days);
		Date d = calendar.getTime();
		return DateUtil.getStrFromDate(d, format);
	}

	/**
	 * 根据当前日期获得所在星期的星期一的年月日
	 * 
	 * @param args
	 */
	public static String getCurrentMonday(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		c.add(Calendar.DATE, -day_of_week + 1);
		String monday = sdf.format(c.getTime());
		return monday;
	}

	/**
	 * 根据当前日期获得所在星期的星期天的年月日
	 * 
	 * @param args
	 */
	public static String getCurrentSunday(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		c.add(Calendar.DATE, -day_of_week + 7);
		String sunday = sdf.format(c.getTime());
		return sunday;
	}

	/**
	 * 当前和time比较 得出时间差 在多少之前
	 * 
	 * @param time
	 * @return
	 */
	public static String getTimeBefore(Date time) {
		Date now = new Date();
		String str = "";
		try {
			long changeValue = now.getTime() - time.getTime();
			long day = changeValue / (24 * 60 * 60 * 1000);
			long hour = (changeValue / (60 * 60 * 1000) - day * 24);
			long min = ((changeValue / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (changeValue / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

			if (day > 0) {
				// str = day + "天";
				return null;
			} else if (hour > 0) {
				str = hour + "小时前";
			} else if (min > 0) {
				str = min + "分前";
			} else {
				str = s + "秒前";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
