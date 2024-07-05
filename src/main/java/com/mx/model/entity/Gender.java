package com.mx.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gender {
	@Id
	@SequenceGenerator(name = "gendersSeq", sequenceName = "genders_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gendersSeq")
	private Long id;
	private String name;
}