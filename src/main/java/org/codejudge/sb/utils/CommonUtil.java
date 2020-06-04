package org.codejudge.sb.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtil {

	public static String getCurrentDateYYYYMMDDMMSSzzz() {
		DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		LocalDateTime dt = LocalDateTime.now();
		return dt.format(dateForm);
	}

	public static Long getRandomValue() {
		Long randomNumber = Long.parseLong(getCurrentDateYYYYMMDDMMSSzzz());
		return randomNumber;
	}

}
