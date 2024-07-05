package com.mx.service;

import com.mx.mapper.EmployeeMapper;
import com.mx.model.entity.Employee;
import com.mx.model.entity.Gender;
import com.mx.model.entity.Job;
import com.mx.model.request.EmployeeHoursRequest;
import com.mx.model.request.EmployeeRequest;
import com.mx.model.request.EmployeeThreadRequest;
import com.mx.model.response.EmployeeHoursResponse;
import com.mx.model.response.EmployeeResponse;
import com.mx.model.response.EmployeeThreadListResponse;
import com.mx.model.response.EmployeeThreadResponse;
import com.mx.repository.EmployeesRepository;
import com.mx.repository.GendersRepository;
import com.mx.repository.JobsRepository;
import com.mx.utils.HoursValidationUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeService {

	@Inject
	GendersRepository gendersRepository;

	@Inject
	JobsRepository jobsRepository;

	@Inject
	EmployeesRepository employeesRepository;

	@Inject
	EmployeeMapper employeeMapper;

	@Transactional
	public Employee addEmployee(EmployeeRequest request) {
		Job job = jobsRepository.findById(request.getJobId());
		Gender gender = gendersRepository.findById(request.getGenderId());

		Employee employee = new Employee();
		employee.setGender(gender);
		employee.setJob(job);
		employee.setName(request.getName());
		employee.setLastName(request.getLastName());

		// Convertir LocalDate a Date
		LocalDate birthdate = request.getBirthdate();
		Date sqlDate = Date.valueOf(birthdate);
		employee.setBirthdate(sqlDate);

		employeesRepository.persist(employee);

		return employee;
	}

	@Transactional
	public List<EmployeeResponse> getEmployeesByJobId(Long jobId) {
		List<Employee> employees = employeesRepository.findByJobId(jobId);
		return employees.stream()
			.map(employeeMapper::mapToEmployeeResponse)
			.collect(Collectors.toList());
	}

	public EmployeeThreadListResponse threadEmployees(EmployeeThreadRequest request) {
		List<Long> employeeIds = request.getEmployeeIds();
		java.sql.Date startDate = java.sql.Date.valueOf(request.getStartDate()) ;
		java.sql.Date  endDate = java.sql.Date.valueOf(request.getEndDate());

		List<EmployeeThreadResponse> employees = new ArrayList<>();
		List<CompletableFuture<Void>> futures = new ArrayList<>();

		/*
		la petición de la lista de IDs del request se maneja de manera que cada ID se procesa en paralelo utilizando hilos separados.
		Esto significa que no se realiza una solicitud por cada ID de forma secuencial uno por uno,
		sino que se envían todas las solicitudes a la vez para que se procesen de manera concurrente
		*/
		for (Long employeeId : employeeIds) {
			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
				Employee employee = employeesRepository.findByIdAndDates(employeeId,startDate, endDate);

				if (employee != null) {
					EmployeeThreadResponse employeeResponse = mapToEmployeeResponse(employee);
					employees.add(employeeResponse);
				}
			});

			futures.add(future);
		}

		// Esperar a que todos los hilos completen
		CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
		try {
			allOf.get();
		} catch (InterruptedException | ExecutionException e) {

			e.printStackTrace();
		}

		EmployeeThreadListResponse response = new EmployeeThreadListResponse();
		response.setEmployees(employees);
		response.setSuccess(true);
		return response;
	}


	private EmployeeThreadResponse mapToEmployeeResponse(Employee employee) {
		EmployeeThreadResponse response = new EmployeeThreadResponse();

		response.setGenderId(employee.getGender().getId());
		response.setJobId(employee.getJob().getId());
		response.setName(employee.getName());
		response.setLastName(employee.getLastName());
		response.setBirthdate(employee.getBirthdate().toString());
		return response;
	}

	public EmployeeHoursResponse getTotalWorkedHours(EmployeeHoursRequest request) {
		try {
			// Validar las fechas utilizando la clase de validación
			if (!HoursValidationUtils.isValidDateRange(request.getStartDate(), request.getEndDate())) {
				return new EmployeeHoursResponse(null, false);
			}

			// Calcular las horas trabajadas del empleado en el rango de fechas
			Integer totalWorkedHours = employeesRepository.getTotalWorkedHours(
				request.getEmployeeId(),
				LocalDate.parse(request.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				LocalDate.parse(request.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
			);


			return new EmployeeHoursResponse(totalWorkedHours, true);
		} catch (Exception e) {

			return new EmployeeHoursResponse(null, false);
		}
	}
}
