package com.springboot.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.model.Employee;

import com.springboot.employee.service.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController

@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@PostMapping("/employees")

	public void createEmployee(@Valid @RequestBody Employee emp) {

		employeeService.createEmployee(emp);

	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {

		return employeeService.fetchAllEmployees();

	}

	@GetMapping("/employees/byactivestate")
	public List<Employee> getEmployeesByIsActiveState() {
		return employeeService.fetchEmployeesByIsActiveState();
	}
	
		
	@PutMapping("/employees/{id}")
	public void updateEmployee(@PathVariable(value = "emp_id") Long emp_id, @Valid @RequestBody Employee emp) {
		logger.info("employe email is.");
		employeeService.updateEmployeeDetails(emp_id, emp);
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		employeeService.deleteEmployeeDetails(employeeId);
	}
}
