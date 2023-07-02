package com.emr.graphql.datasource.repository;

import com.emr.graphql.datasource.entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends CrudRepository<Patient, UUID> {
}
