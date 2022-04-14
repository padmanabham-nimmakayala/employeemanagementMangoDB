package com.springboot.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.employee.model.Project;
@Repository
public interface ProjectRepository extends MongoRepository<Project,Long> {

}
