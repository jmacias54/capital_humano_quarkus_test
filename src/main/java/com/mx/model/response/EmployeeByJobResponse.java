package com.mx.model.response;

import lombok.Builder;

import java.util.List;

@Builder
public class EmployeeByJobResponse {

	private List<EmployeeResponse> employees;
	private boolean success;

	public List<EmployeeResponse> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeResponse> employees) {
		this.employees = employees;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
