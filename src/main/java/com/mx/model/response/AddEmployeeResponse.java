package com.mx.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddEmployeeResponse {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("success")
	private boolean success;

	public AddEmployeeResponse(Long id, boolean success) {
		this.id = id;
		this.success = success;
	}
}
