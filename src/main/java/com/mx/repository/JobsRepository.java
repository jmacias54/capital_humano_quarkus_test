package com.mx.repository;

import com.mx.model.entity.Job;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JobsRepository implements PanacheRepository<Job> {
	
}
