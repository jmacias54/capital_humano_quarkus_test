package com.mx.validations;

import com.mx.repository.GendersRepository;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderExistsValidator implements ConstraintValidator<GenderExists, Long> {

	@Inject
	GendersRepository gendersRepository;

	@Override
	public void initialize(GenderExists constraintAnnotation) {
	}

	@Override
	public boolean isValid(Long genderId, ConstraintValidatorContext context) {
		if (genderId == null) {
			return false;
		}
		return gendersRepository.findById(genderId) != null;
	}
}
