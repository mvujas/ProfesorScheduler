package utils;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class DateUtils {
	private DateUtils() {}
	
	private static DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("dd. MM. yyyy.");
	public static String getDatumAsString(LocalDateTime dateTime) {
		if(dateTime == null) {
			throw new NullPointerException("dateTime mustn't be null!");
		}
		return DATE_FORMATTER.print(dateTime);
	}
	
	private static DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH:mm");
	public static String getTimeAsString(LocalDateTime dateTime) {
		if(dateTime == null) {
			throw new NullPointerException("dateTime mustn't be null!");
		}
		return TIME_FORMATTER.print(dateTime);
	}
	
	public static long utcToMillis(int UTC) {
		return (UTC - 3600) * 1000L;
	}
	
}
