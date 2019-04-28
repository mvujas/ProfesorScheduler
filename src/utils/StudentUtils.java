package utils;

import java.util.regex.Pattern;

public final class StudentUtils {
	private StudentUtils() {}
	
	public static boolean isBrojIndeksaValid(String brIndeksa) {
		return brIndeksa != null && 
				brIndeksaPattern.matcher(brIndeksa).matches();
	}
	
	public static boolean isImeValid(String ime) {
		return ime != null &&
				ime.trim().length() > 0;
	}
	
	public static boolean isPrezimeValid(String prezime) {
		return isImeValid(prezime);
	}
	
	private static final Pattern brIndeksaPattern = Pattern.compile(
			"^\\s*?\\d{1,5}/\\d{2}\\s*?$");
}
