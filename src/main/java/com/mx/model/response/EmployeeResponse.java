package com.mx.model.response;

public class EmployeeResponse {
	private Long id;
	private String name;
	private String lastName;
	private String birthdate;
	private JobResponse job;
	private GenderResponse gender;

	// Getters y setters para EmployeeResponse
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public JobResponse getJob() {
		return job;
	}

	public void setJob(JobResponse job) {
		this.job = job;
	}

	public GenderResponse getGender() {
		return gender;
	}

	public void setGender(GenderResponse gender) {
		this.gender = gender;
	}
}
