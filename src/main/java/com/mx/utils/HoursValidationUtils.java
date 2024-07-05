package com.mx.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HoursValidationUtils {

	public static boolean isValidDateRange(String startDateStr, String endDateStr) {
		try {
			LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			return !startDate.isAfter(endDate);
		} catch (Exception e) {
			return false;
		}
	}
}
