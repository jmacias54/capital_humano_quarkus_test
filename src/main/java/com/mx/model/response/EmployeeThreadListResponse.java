package com.mx.model.response;

import java.util.List;

public class EmployeeThreadListResponse {

	private List<EmployeeThreadResponse> employees;
	private boolean success;

	public List<EmployeeThreadResponse> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeThreadResponse> employees) {
		this.employees = employees;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
