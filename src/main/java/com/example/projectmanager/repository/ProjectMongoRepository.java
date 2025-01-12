package com.example.projectmanager.repository;

import com.example.projectmanager.domain.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface ProjectMongoRepository extends CrudRepository<Project, BigInteger> {
}
