package com.mx.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeHoursResponse {
	private Integer totalWorkedHours;
	private boolean success;


}
