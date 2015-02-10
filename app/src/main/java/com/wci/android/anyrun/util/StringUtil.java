package com.wci.android.anyrun.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static String trim(String s, int length) {
		return StringUtil.trim(s, length, "...");
	}
	
	public static String trim(String s, int length, String ellipse) {
		if (s == null)
			return "";
		if (s.length() > length) {
			return s.substring(0, length - ellipse.length()) + ellipse;
		} else {
			return s;
		}
	}
	
	public static String notNull(String s) {
		if (s == null || s.equals("null")) {
			return "";
		} else {
			return s;
		}
	}
	
	public static final Pattern	VALID_EMAIL_ADDRESS_REGEX	= Pattern.compile(
			"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	
	public static boolean isValidEmail(String emailStr) {
		if (emailStr == null || emailStr.isEmpty()) { return false; }
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
	
}
