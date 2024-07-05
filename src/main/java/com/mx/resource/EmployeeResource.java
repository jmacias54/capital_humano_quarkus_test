package com.mx.resource;

import com.mx.model.entity.Employee;
import com.mx.model.request.EmployeeHoursRequest;
import com.mx.model.request.EmployeeRequest;
import com.mx.model.request.EmployeeThreadRequest;
import com.mx.model.request.JobRequest;
import com.mx.model.response.*;
import com.mx.service.EmployeeService;
import com.mx.validations.JobExists;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

	@Inject
	EmployeeService employeeService;

	@Inject
	Validator validator;

	@POST
	@Path("/add")
	public Response addEmployee(EmployeeRequest request) {
		Set<ConstraintViolation<EmployeeRequest>> violations = validator.validate(request);
		if(!violations.isEmpty()) {
			String errorMessages = violations.stream()
				.map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
				.collect(Collectors.joining(", "));
			throw new BadRequestException(errorMessages);
		}

		try {
			Employee employee = employeeService.addEmployee(request);
			return Response.ok(new AddEmployeeResponse(employee.getId(), true)).build();
		} catch(IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(new AddEmployeeResponse(null, false))
				.build();
		} catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(new AddEmployeeResponse(null, false))
				.build();
		}
	}

	@POST
	@Path("/findByJob")
	public Response getEmployeesByJob(JobRequest request) {
		Set<ConstraintViolation<JobRequest>> violations = validator.validate(request);
		if(!violations.isEmpty()) {
			String errorMessages = violations.stream()
				.map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
				.collect(Collectors.joining(", "));
			throw new BadRequestException(errorMessages);
		}

		try {

			List<EmployeeResponse> employees = employeeService.getEmployeesByJobId(request.getJobId());
			return Response.ok(EmployeeByJobResponse.builder().employees(employees).success(Boolean.TRUE).build())
				.build();
		} catch(Exception e) {
			return Response.ok(EmployeeByJobResponse.builder().build()).build();
		}
	}

	@POST
	@Path("/byJobAndGroupedByLastName")
	public Response getEmployeesByJobAndGroupedByLastName(@Valid JobRequest request) {
		try {
			Map<String, List<EmployeeResponse>> employeesGrouped = employeeService.getEmployeesByJobIdGroupedByLastName(
				request.getJobId());
			return Response.ok(employeesGrouped).build();
		} catch(Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Error fetching employees").build();
		}
	}

	@POST
	@Path("/thread")
	public Response threadEmployees(EmployeeThreadRequest request) {
		try {

			EmployeeThreadListResponse response = employeeService.threadEmployees(request);
			return Response.ok(response)
				.build();
		} catch(Exception e) {
			return Response.ok(EmployeeByJobResponse.builder().build()).build();
		}
	}

	@POST
	@Path("/getTotalWorkedHours")
	public Response getTotalWorkedHours(@Valid EmployeeHoursRequest request) {
		try {
			EmployeeHoursResponse response = employeeService.getTotalWorkedHours(request);
			return Response.ok(response)
				.build();
		} catch(Exception e) {
			return Response.ok(EmployeeByJobResponse.builder().build()).build();
		}
	}
}
