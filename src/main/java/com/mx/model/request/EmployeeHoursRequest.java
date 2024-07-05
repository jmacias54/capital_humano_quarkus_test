package com.mx.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeHoursRequest {

	@NotNull
	private Long employeeId;

	@NotNull
	private String startDate;

	@NotNull
	private String endDate;


}
