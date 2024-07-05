package com.mx.model.request;

import com.mx.validations.EmployeeExists;
import com.mx.validations.ValidDateRange;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ValidDateRange(startDateField = "startDate", endDateField = "endDate")
public class PaymentRequest {

	@NotNull
	@EmployeeExists
	private Long employeeId;

	@NotNull
	private String startDate;

	@NotNull
	private String endDate;
}
