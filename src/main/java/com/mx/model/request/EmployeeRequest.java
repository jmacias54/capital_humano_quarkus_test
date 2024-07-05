package com.mx.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.validations.Adult;
import com.mx.validations.GenderExists;
import com.mx.validations.JobExists;
import com.mx.validations.UniqueEmployee;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@UniqueEmployee
public class EmployeeRequest {

	@JsonProperty("gender_id")
	@NotNull(message = "El ID del Genero no puede ser nulo.")
	@GenderExists
	private Long genderId;

	@JsonProperty("job_id")
	@NotNull(message = "El ID de puesto no puede ser nulo.")
	@JobExists
	private Long jobId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("birthdate")
	@NotNull
	@Adult
	private LocalDate birthdate;
}
