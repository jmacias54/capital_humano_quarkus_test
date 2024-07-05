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
public class Employee {
	@Id
	@SequenceGenerator(name = "employeesSeq", sequenceName = "employees_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeesSeq")
	private Long id;
	@ManyToOne
	private Gender gender;
	@ManyToOne
	private Job job;
	private String name;
	private String lastName;
	private Date birthdate;
}
