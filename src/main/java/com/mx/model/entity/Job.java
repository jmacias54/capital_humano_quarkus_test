package com.mx.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
	@Id
	@SequenceGenerator(name = "jobsSeq", sequenceName = "jobs_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobsSeq")
	private Long id;
	private String name;
	private Double salary;
}
