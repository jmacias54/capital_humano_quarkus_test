package com.mx.repository;

import com.mx.model.entity.Employee;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class EmployeesRepository implements PanacheRepository<Employee>  {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public List<Employee> findByJobId(Long jobId) {
		return list("job.id", jobId);
	}

	public Employee findByIdAndDates(Long employeeId, Date startDate, Date endDate) {
		return find("id = :employeeId and birthdate between :startDate and :endDate",
			Parameters.with("employeeId", employeeId)
				.and("startDate", startDate)
				.and("endDate", endDate))
			.firstResult();
	}

	public Integer getTotalWorkedHours(Long employeeId, LocalDate startDate, LocalDate endDate) {
		String query = "SELECT SUM(e.workedHours) " +
			"FROM EmployeeWorkedHours e " +
			"WHERE e.employee.id = :employeeId " +
			"AND e.workedDate BETWEEN :startDate AND :endDate";

		return entityManager.createQuery(query, Long.class)
			.setParameter("employeeId", employeeId)
			.setParameter("startDate", startDate)
			.setParameter("endDate", endDate)
			.getSingleResult()
			.intValue();
	}

}
