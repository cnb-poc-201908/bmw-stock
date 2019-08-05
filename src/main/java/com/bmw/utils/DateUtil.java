package com.bmw.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.JulianFields;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private DateUtil() {
		
	}

	
	public static String toJulianString(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
		LocalDate formatted = LocalDate.parse(dateString,formatter); 
		return toJulianString(formatted);
	}
	
    public static String toJulianString(LocalDate date) {
		long julianDate = JulianFields.JULIAN_DAY.getFrom(date);
		return String.valueOf(julianDate);
    }
	
    public static String toJulianString(Date date) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
		LocalDate localDate = LocalDate.of(
				calendar.get(Calendar.YEAR) , 
				calendar.get(Calendar.MONTH) + 1, 
				calendar.get(Calendar.DAY_OF_MONTH));
		
		long julianDate = JulianFields.JULIAN_DAY.getFrom ( localDate );
		return String.valueOf(julianDate);
    }
}
