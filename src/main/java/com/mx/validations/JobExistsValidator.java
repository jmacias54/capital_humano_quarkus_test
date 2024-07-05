package com.mx.validations;

import com.mx.repository.JobsRepository;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class JobExistsValidator implements ConstraintValidator<JobExists, Long> {

	@Inject
	JobsRepository jobsRepository;

	@Override
	public void initialize(JobExists constraintAnnotation) {
	}

	@Override
	public boolean isValid(Long jobId, ConstraintValidatorContext context) {
		if (jobId == null) {
			return false;
		}
		return jobsRepository.findById(jobId) != null;
	}
}
