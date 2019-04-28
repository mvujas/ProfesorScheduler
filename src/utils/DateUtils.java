package utils;

import java.util.Date;

public final class DateUtils {
	private DateUtils() {}
	
	public static Date intToDate(int unixTime) {
		return new Date(unixTime * 1000L);
	}
	
}
