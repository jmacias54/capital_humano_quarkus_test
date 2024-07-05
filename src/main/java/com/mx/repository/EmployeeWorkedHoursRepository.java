package com.mx.repository;

import com.mx.model.entity.EmployeeWorkedHours;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeWorkedHoursRepository implements PanacheRepository<EmployeeWorkedHours> {
}