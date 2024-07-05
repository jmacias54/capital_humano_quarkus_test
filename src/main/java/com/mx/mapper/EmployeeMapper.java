package com.mx.mapper;


import com.mx.model.entity.Employee;
import com.mx.model.entity.Gender;
import com.mx.model.entity.Job;
import com.mx.model.response.EmployeeResponse;
import com.mx.model.response.GenderResponse;
import com.mx.model.response.JobResponse;
import jakarta.enterprise.context.ApplicationScoped;
import com.mx.model.response.EmployeeByJobResponse;

@ApplicationScoped
public class EmployeeMapper {

	public EmployeeResponse mapToEmployeeResponse(Employee employee) {
		EmployeeResponse response = new EmployeeResponse();
		response.setId(employee.getId());
		response.setName(employee.getName());
		response.setLastName(employee.getLastName());
		response.setBirthdate(employee.getBirthdate().toString());
		response.setJob(mapToJobResponse(employee.getJob()));
		response.setGender(mapToGenderResponse(employee.getGender()));

		return response;
	}

	private JobResponse mapToJobResponse(Job job) {
		JobResponse jobResponse = new JobResponse();
		jobResponse.setId(job.getId());
		jobResponse.setName(job.getName());
		jobResponse.setSalary(job.getSalary());
		return jobResponse;
	}

	private GenderResponse mapToGenderResponse(Gender gender) {
		GenderResponse genderResponse = new .GenderResponse();
		genderResponse.setId(gender.getId());
		genderResponse.setName(gender.getName());
		return genderResponse;
	}
}
