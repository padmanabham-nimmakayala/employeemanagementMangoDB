package com.springboot.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.employee.model.Employee;
import com.springboot.employee.model.Project;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {

	public Employee findByEmail(String email);
	

}
