package com.mx.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {

	private static final int ADULT_AGE = 18;

	@Override
	public void initialize(Adult constraintAnnotation) {
	}

	@Override
	public boolean isValid(LocalDate birthdate, ConstraintValidatorContext context) {
		if (birthdate == null) {
			return false;
		}
		LocalDate now = LocalDate.now();
		return Period.between(birthdate, now).getYears() >= ADULT_AGE;
	}
}