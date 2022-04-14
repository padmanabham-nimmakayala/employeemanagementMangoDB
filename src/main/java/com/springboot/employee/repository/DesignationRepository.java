package com.springboot.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.employee.model.Designation;
@Repository
public interface DesignationRepository extends MongoRepository<Designation, Integer> {

}
