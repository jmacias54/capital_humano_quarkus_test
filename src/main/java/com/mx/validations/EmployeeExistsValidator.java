package com.mx.validations;

import com.mx.repository.EmployeesRepository;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeExistsValidator implements ConstraintValidator<EmployeeExists, Long> {

	@Inject
	EmployeesRepository employeesRepository;

	@Override
	public void initialize(EmployeeExists constraintAnnotation) {
	}

	@Override
	public boolean isValid(Long employeeId, ConstraintValidatorContext context) {
		if (employeeId == null) {
			return false;
		}
		return employeesRepository.findById(employeeId) != null;
	}
}