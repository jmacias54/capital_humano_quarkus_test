package com.mx.validations;

import com.mx.model.request.EmployeeRequest;
import com.mx.repository.EmployeesRepository;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmployeeValidator implements ConstraintValidator<UniqueEmployee, EmployeeRequest> {

	@Inject
	EmployeesRepository employeesRepository;

	@Override
	public void initialize(UniqueEmployee constraintAnnotation) {
	}

	@Override
	public boolean isValid(EmployeeRequest request, ConstraintValidatorContext context) {
		if (request.getName() == null || request.getLastName() == null) {
			return true; // Not applicable for null values, handled by other annotations
		}
		return employeesRepository.count("name = ?1 and lastName = ?2", request.getName(), request.getLastName()) == 0;
	}
}