package com.emr.graphql.datasource.repository;

import com.emr.graphql.datasource.entity.Solution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SolutionRepository extends CrudRepository<Solution, UUID> {
}
