package com.mx.model.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWorkedHours {
	@Id
	@SequenceGenerator(name = "employeeWorkedHoursSeq", sequenceName = "employee_worked_hours_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeWorkedHoursSeq")
	private Long id;
	@ManyToOne
	private Employee employee;
	private Double workedHours;
	private Date workedDate;
}
