package com.mx.repository;

import com.mx.model.entity.EmployeeWorkedHours;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class EmployeeWorkedHoursRepository implements PanacheRepository<EmployeeWorkedHours> {

	public List<EmployeeWorkedHours> findByEmployeeIdAndDateRange(
		Long employeeId,
		LocalDate startDate,
		LocalDate endDate
	) {
		return find("employee_id = :employeeId and worked_date between :startDate and :endDate",
			employeeId, startDate, endDate
		).list();
	}
}