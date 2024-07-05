package com.mx.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, Object> {

	private String startDateField;
	private String endDateField;

	@Override
	public void initialize(ValidDateRange constraintAnnotation) {
		this.startDateField = constraintAnnotation.startDateField();
		this.endDateField = constraintAnnotation.endDateField();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			Field startDateField = value.getClass().getDeclaredField(this.startDateField);
			Field endDateField = value.getClass().getDeclaredField(this.endDateField);

			startDateField.setAccessible(true);
			endDateField.setAccessible(true);

			String startDateString = (String) startDateField.get(value);
			String endDateString = (String) endDateField.get(value);

			LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			return !startDate.isAfter(endDate);
		} catch (Exception e) {
			return false;
		}
	}
}
