package utils;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
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
	public static String getDatumAsString(LocalDate dateTime) {
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
	public static String getTimeAsString(LocalTime dateTime) {
		if(dateTime == null) {
			throw new NullPointerException("dateTime mustn't be null!");
		}
		return TIME_FORMATTER.print(dateTime);
	}
	
	public static LocalDate javaLocalDateToJodaLocalDate(java.time.LocalDate javaDate) {
		return new LocalDate(java.util.Date.from(javaDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
	}
	
	public static java.time.LocalDate jodaLocalDatetoJavaLocalDate(LocalDate jodaDate) {
		return java.time.LocalDate.of(jodaDate.getYear(), jodaDate.getMonthOfYear(), jodaDate.getDayOfMonth());
	}
	
	public static long utcToMillis(int UTC) {
		return (UTC - 3600) * 1000L;
	}
	
}
