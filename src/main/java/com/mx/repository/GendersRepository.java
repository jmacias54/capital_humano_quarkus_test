package com.mx.repository;

import com.mx.model.entity.Gender;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GendersRepository implements PanacheRepository<Gender> {
}
