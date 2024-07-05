package com.mx.model.request;

import com.mx.validations.JobExists;
import jakarta.validation.constraints.NotNull;

public class JobRequest {
	@NotNull(message = "El id del puesto no puede ser nulo.")
	@JobExists
	private Long jobId;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
}
