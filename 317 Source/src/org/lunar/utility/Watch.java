package org.lunar.utility;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * The {@link Watch} class contains methods which
 * handle anything with the time, month, year, etc...
 * 
 * @author Michael P
 *
 */
public class Watch {

	/**
	 * The {@link Calendar} which is set on the TimeZone of which the Lunar source
	 * is running from. For example: if the server is running in a
	 * UTC - 05:00 (Eastern time zone for USA & Canada) the time zone would be
	 * UTX - 05:00.
	 */
	private static final Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
	
	/**
	 * Gets the current date.
	 * @return The {@link Date}.
	 */
	public static Date getDate() {
		return calendar.getTime();
	}
	
	/**
	 * Gets the current time in String format.
	 * @return The current time which contains the day, month, time and year.
	 */
	public static String getTime() {
		return calendar.getTime().toString();
	}
	
	/**
	 * Gets the watch's calendar.
	 * @return The {@link Calendar} of the watch.
	 */
	public static Calendar getCalendar() {
		return calendar;
	}
	
}
