package utils;

import java.text.ParseException;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class DateUtils {
	private DateUtils() {}
	
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
	
	
	public static LocalTime strToLocalTime(String strTime) throws ParseException {
		try {
			return TIME_FORMATTER.parseLocalTime(strTime);
		} catch(Exception e) {
			throw new ParseException("Nevalidno vreme", 0);
		}
	}
	
	public static long localDateTimeToUTCTimestamp(LocalDateTime dateTime) {
		DateTime utc = dateTime.toDateTime(DateTimeZone.UTC);
		long secondsSinceEpoch = utc.getMillis() / 1000;
		return secondsSinceEpoch;
	}
	
	
	private static final Pattern TIME_PATTERN = Pattern.compile("^\\d{2}:\\d{2}&");
	private static DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH:mm");
	private static DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("dd. MM. yyyy.");
}
