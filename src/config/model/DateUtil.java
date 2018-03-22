package  config.model;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;


public class DateUtil {

	public static final String DATE_FORMAT = "dd-MM-yyyy";

	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static char DATE_FORMAT_DELIMITER = '-';

	public static char TIME_FORMAT_DELIMITER = ':';

	public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

	public static final String TIME_FORMAT = "HH:mm:ss";

	public static final String TIME_ONLY_FORMAT = "HH:mm";
	
	public static java.sql.Timestamp currentSQLTime() {
		 return new java.sql.Timestamp(DateUtil.currentDateTime().getTime());
	}

	public static String sqLTimeToString(java.sql.Timestamp timeStamp) {
		 return dateToString(new Date(timeStamp.getTime()));
	}	

	public static String sqLTimeToString2(long timeStamp) {
		 return dateToString(new Date(timeStamp));
	}
	
	public static Date createDate(int year, int month, int day) {

		Calendar cal = Calendar.getInstance();
		cal = DateUtil.clearTime(cal);
		cal.set(year, month - 1, day); // Calendar months start with 0

		return cal.getTime();
	}

	public static boolean isAfterOrEqual(Date date1, Date date2) {
		return (date1.after(date2) || dateToString(date1).equals(
				dateToString(date2)));
	}

	public static boolean isBetweenOrEqual(Date date, Date minDate, Date maxDate) {
		return ((date.after(minDate) || dateToString(date).equals(
				dateToString(minDate))) && (date.before(maxDate) || dateToString(
				date).equals(dateToString(maxDate))));
	}



	public static boolean isBeforeOrEqual(Date date1, Date date2) {
		return (date1.before(date2) || dateToString(date1).equals(
				dateToString(date2)));
	}

	public static boolean isBefore(Date date1, Date date2) {
		return date1.before(date2);
	}

	public static Date getMonthFirstDate(Date monthAnyDate) {
		Calendar calendar = dateToCalendar(monthAnyDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		return getMonthFirstDay(year, month + 1);
	}

	public static Date getMonthLastDate(Date monthAnyDate) {
		Calendar calendar = dateToCalendar(monthAnyDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		return getMonthLastDay(year, month + 1);
	}

	public static Date getMonthFirstDay(int year, int month) {

		// reate the first date of the month-year
		Calendar cal = Calendar.getInstance();
		cal = DateUtil.clearTime(cal);
		cal.set(year, month - 1, 1); // Calendar months start with 0

		return cal.getTime();
	}

	public static Date getMonthLastDay(int year, int month) {

		// create the last date of the month-year
		Calendar cal = Calendar.getInstance();
		cal = DateUtil.clearTime(cal);
		cal.set(year, month - 1, 1); // Calendar months start with 0

		int maxDay = 0;
		switch (month) {
		case 1:
			maxDay = 31;
			break;
		case 2:
			if (year % 4 == 0) {
				maxDay = 29;
			} else {
				maxDay = 28;
			}
			;
			break;
		case 3:
			maxDay = 31;
			break;
		case 4:
			maxDay = 30;
			break;
		case 5:
			maxDay = 31;
			break;
		case 6:
			maxDay = 30;
			break;
		case 7:
			maxDay = 31;
			break;
		case 8:
			maxDay = 31;
			break;
		case 9:
			maxDay = 30;
			break;
		case 10:
			maxDay = 31;
			break;
		case 11:
			maxDay = 30;
			break;
		case 12:
			maxDay = 31;
			break;
		}

		cal.set(Calendar.DAY_OF_MONTH, maxDay);

		return cal.getTime();
	}

	public static int getMonthDays(Date date) {

		Calendar calendarDate = dateToCalendar(date);
		int month = calendarDate.get(Calendar.MONTH) + 1;
		int year = calendarDate.get(Calendar.YEAR);

		int maxDay = 0;
		switch (month) {
		case 1:
			maxDay = 31;
			break;
		case 2:
			if (year % 4 == 0) {
				maxDay = 29;
			} else {
				maxDay = 28;
			}
			;
			break;
		case 3:
			maxDay = 31;
			break;
		case 4:
			maxDay = 30;
			break;
		case 5:
			maxDay = 31;
			break;
		case 6:
			maxDay = 30;
			break;
		case 7:
			maxDay = 31;
			break;
		case 8:
			maxDay = 31;
			break;
		case 9:
			maxDay = 30;
			break;
		case 10:
			maxDay = 31;
			break;
		case 11:
			maxDay = 30;
			break;
		case 12:
			maxDay = 31;
			break;
		}

		return maxDay;
	}

	public static int getDayOfMonth(Date date) {

		Calendar calendar = dateToCalendar(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth(Date date) {

		Calendar calendar = dateToCalendar(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getYear(Date date) {

		Calendar calendar = dateToCalendar(date);
		return calendar.get(Calendar.YEAR);
	}

	public static Calendar addMonths(Calendar calendarDate, int months) {
		Calendar cal = (Calendar) calendarDate.clone();
		cal.add(Calendar.MONTH, months);
		return cal;
	}

	public static Calendar addDay(Calendar calendarDate, int days) {
		Calendar cal = (Calendar) calendarDate.clone();
		cal.add(Calendar.DATE, days);
		return cal;
	}

	public static Calendar addMinute(Calendar calendarDate, int minutes) {
		Calendar cal = (Calendar) calendarDate.clone();
		cal.add(Calendar.MINUTE, minutes);
		return cal;
	}

	public static Date addDay(Date date, int days) {
		return calendarToDate(addDay(dateToCalendar(date), days));
	}

	public static Date addMinute(Date date, int minute) {
		return calendarToDate(addMinute(dateToCalendar(date), minute));
	}

	public static int getDaysFromToday(java.util.Date theDate) {
		return daysBetween(theDate, new java.sql.Date(System.currentTimeMillis()));
	}

	public static int daysBetween(Calendar startDate, Calendar endDate) {
		return daysBetween(startDate.getTime(), endDate.getTime());
	}

	public static int daysBetween(java.util.Date startDate,
			java.util.Date endDate) {
		long dayMillis = 86400000;
		return (int) ((long) Math.abs((long) (endDate.getTime() - startDate
				.getTime())) / dayMillis);
	}

	public static int yearsBetween(Calendar startCalendar, Calendar endCalendar) {
		int count = 0;
		for (startCalendar.add(Calendar.YEAR, 1); startCalendar
				.compareTo(endCalendar) <= 0; endCalendar.add(Calendar.YEAR, 1)) {
			count++;
		}
		return count;
	}

	public static int monthsBetween(Calendar startCalendar, Calendar endCalendar) {
		int count = 0;
		for (startCalendar.add(Calendar.MONTH, 1); startCalendar
				.compareTo(endCalendar) <= 0; startCalendar.add(Calendar.MONTH,
				1)) {
			count++;
		}
		return count;
	}

	public static int yearsBetween(Date startDate, Date endDate) {
		return DateUtil.yearsBetween(DateUtil.dateToCalendar(startDate),
				DateUtil.dateToCalendar(endDate));
	}

	public static int monthsBetween(Date startDate, Date endDate) {
		return DateUtil.monthsBetween(DateUtil.dateToCalendar(startDate),
				DateUtil.dateToCalendar(endDate));
	}

	public static String calendarToString(Calendar calendar) {

		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		String dateString = formatter.format(calendar.getTime());
		return dateString;
	}

	public static String dateToString(Date date) {

		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		String dateString = formatter.format(date);
		return dateString;
	}

	public static String dateToString(Date date, String format) {

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(date);
		return dateString;
	}

	public static String timeToString(Date date) {

		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT);
		String dateString = formatter.format(date);
		return dateString;
	}

	public static String timeToString(Date date,String format) {

		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(date);
		return dateString;
	}
	
	public static String dateTimeToString(Date date) {

		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		String dateString = formatter.format(date);
		return dateString;
	}

	public static Date stringToDate(String str) {
		if (str.isEmpty())
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		ParsePosition pos = new ParsePosition(0);
		Date currentDate = formatter.parse(str, pos);
		return currentDate;
	}
	
	public static Date stringToTime(String str) {
		if (str.isEmpty())
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(TIME_ONLY_FORMAT);
		ParsePosition pos = new ParsePosition(0);
		Date currentDate = formatter.parse(str, pos);
		return currentDate;
	}

	public static Calendar stringToCalendar(String str) {

		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		ParsePosition pos = new ParsePosition(0);
		Date currentDate = formatter.parse(str, pos);
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		return cal;
	}

	public static Date stringToDate(String str, String format) {
		
//		if(StringUtil.isStringEmpty(format))
//			return new Date(str);

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		Date currentDate = formatter.parse(str, pos);
		return currentDate;
	}

	public static Calendar stringToCalendar(String str, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		Date currentDate = formatter.parse(str, pos);
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		return cal;
	}

	public static Date currentDate() {
		Calendar rightNow = Calendar.getInstance();
		clearTime(rightNow);
		return rightNow.getTime();
	}

	public static Date currentDateTime() {
		Calendar rightNow = Calendar.getInstance();
		return rightNow.getTime();
	}

	public static Calendar currentCalendar() {

		Calendar rightNow = Calendar.getInstance();
		clearTime(rightNow);
		return rightNow;
	}

	public static Date currentTime() {

		Calendar rightNow = Calendar.getInstance();
		return rightNow.getTime();
	}

	/**
	 * Zero the time information (00:00:00:0) from cal and return new Calendar
	 * object. The cal's values keep unchanged
	 * 
	 * @param cal
	 *            initial calendar object
	 * @return the same Calendar object with zeroed time (00:00:00:0)
	 */
	static public Calendar clearTime(Calendar cal) {
		int nYear = cal.get(Calendar.YEAR);
		int nMonth = cal.get(Calendar.MONTH);
		int nDay = cal.get(Calendar.DATE);

		// cut off, hour:minute:second:mili informations
		// (set them to 00:00:00:0)
		cal.clear();
		cal.set(nYear, nMonth, nDay);

		return cal;

	} // cutOffTimeInfoFromMe



	static public Date formatDate(Date date) {
		return stringToDate(dateToString(date));
	}

	static public Date formatDate(Date date, String format) {
		return stringToDate(dateToString(date, format), format);
	}

	public static boolean isValid(String strDate, String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		ParsePosition pos = new ParsePosition(0);
		formatter.setLenient(false);
		try {
			if (strDate == null)
				return false;
			strDate = strDate.trim();
			if(dateFormat.equals(DATE_FORMAT)){
				int index = strDate.lastIndexOf(DATE_FORMAT_DELIMITER);
				if (index == -1)
					return false;

				String year = strDate.substring(index + 1);
				if (year.length() != 4)
					return false;
			}
			else if(dateFormat.equals(DATE_FORMAT_YYYY_MM_DD)){
				int index = strDate.lastIndexOf(DATE_FORMAT_DELIMITER);
				if (index == -1)
					return false;

				String day = strDate.substring(index + 1);
				if (day.length() != 2)
					return false;
			}
			else if(dateFormat.equals(DATE_TIME_FORMAT)){
				int index = strDate.lastIndexOf(TIME_FORMAT_DELIMITER);
				if (index == -1)
					return false;

				String seconds = strDate.substring(index + 1);
				if (seconds.length() != 2)
					return false;
			}

			Date d = formatter.parse(strDate, pos);
			if (d == null)
				return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isValid(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		ParsePosition pos = new ParsePosition(0);
		formatter.setLenient(false);
		try {
			if (strDate.isEmpty())
				return false;
			strDate = strDate.trim();
			int index = strDate.lastIndexOf(DATE_FORMAT_DELIMITER);
			if (index == -1)
				return false;
			String year = strDate.substring(index + 1);
			if (year.length() != 4)
				return false;

			Date d = formatter.parse(strDate, pos);
			if (d == null)
				return false;
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public static boolean isValidDateOrMonth(String strDate) {

		boolean validDate = DateUtil.isValid(strDate);
		boolean validMonths = DateUtil.isValid(strDate, "MM-yyyy");

		return validDate || validMonths;
	}

	public static Date calendarToDate(Calendar calendar) {
		if (calendar == null)
			return null;
		return calendar.getTime();
	}

	public static Calendar dateToCalendar(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static boolean isTheSameYearMonth(Date date1, Date date2) {
		return getMonth(date1) == getMonth(date2)
				&& getYear(date1) == getYear(date2);
	}


	public static Date getDateIfValid(String strDate){
		if(!isValid(strDate))
			return null;
		return stringToDate(strDate); 
	}

	public static Date getDateIfValid(String strDate, String format){
		if(!isValid(strDate,format))
			return null;
		return stringToDate(strDate,format); 
	}
	public static Calendar getCalendarIfValid(String strDate){
		if(!isValid(strDate))
			return null;
		return stringToCalendar(strDate); 
	}

	public static Calendar getCalendarIfValid(String strDate, String format){
		if(!isValid(strDate,format))
			return null;
		return stringToCalendar(strDate,format); 
	}

	

	public static int getDaysInMonth(int year, int month) {

		// create the last date of the month-year
		int maxDay = 0;
		switch (month) {
		case 1:
			maxDay = 31;
			break;
		case 2:
			if (year % 4 == 0) {
				maxDay = 29;
			} else {
				maxDay = 28;
			}
			;
			break;
		case 3:
			maxDay = 31;
			break;
		case 4:
			maxDay = 30;
			break;
		case 5:
			maxDay = 31;
			break;
		case 6:
			maxDay = 30;
			break;
		case 7:
			maxDay = 31;
			break;
		case 8:
			maxDay = 31;
			break;
		case 9:
			maxDay = 30;
			break;
		case 10:
			maxDay = 31;
			break;
		case 11:
			maxDay = 30;
			break;
		case 12:
			maxDay = 31;
			break;
		}
		
		return maxDay;
	}
}