package com.springboot.employee.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.employee.model.Designation;
import com.springboot.employee.model.Employee;
import com.springboot.employee.model.Project;
import com.springboot.employee.repository.DesignationRepository;
import com.springboot.employee.repository.EmployeeRepository;
import com.springboot.employee.repository.ProjectRepository;

@Service
public class EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DesignationRepository designationRepository;

	@Autowired
	private ProjectRepository projectRepository;

	public ResponseEntity<String> createEmployee(@Valid Employee emp) {
		Employee employeeEmail = employeeRepository.findByEmail(emp.getEmail());
		logger.info("employeeEmail in createEmployee of EmployeeController is**************" + employeeEmail);
		if (employeeEmail == null) {

			Project pro = new Project();
			projectRepository.save(pro);

			Designation dsg = new Designation();
			designationRepository.save(dsg);

			employeeRepository.save(emp);

			return new ResponseEntity<String>("Emplyee created", HttpStatus.OK);
		} else {
			logger.info("Emplyee already exists with the email**************");
			return new ResponseEntity<String>("Emplyee already exists with the email", HttpStatus.BAD_REQUEST);
		}

	}

	public void updateEmployeeDetails(Long emp_id, @Valid Employee emp) {
		Optional<Employee> empDetails = employeeRepository.findById(emp_id);
		if (empDetails.isPresent()) {
			Employee dbEmployee = empDetails.get();
			dbEmployee.setEmail(emp.getEmail());
			dbEmployee.setFirstName(emp.getFirstName());
			dbEmployee.setLastName(emp.getLastName());
			dbEmployee.setDateOfBirth(emp.getDateOfBirth());
			dbEmployee.setReportingManager(emp.getReportingManager());
			dbEmployee.setMobileNo(emp.getMobileNo());
			dbEmployee.setModifiedDate(new Date());

			dbEmployee.setDesignation(emp.getDesignation());
			dbEmployee.setProjects(emp.getProjects());

			employeeRepository.save(dbEmployee);
		} else {
			System.out.println("Employee record is not exists with this id" + emp_id);
		}

	}

	public List<Employee> fetchAllEmployees() {
		logger.info("Entered  Depo **************");
		List<Employee> ls = employeeRepository.findAll();
		return ls;

	}

	public void deleteEmployeeDetails(Long employeeId) {
		Optional<Employee> empDetails = employeeRepository.findById(employeeId);
		if (empDetails.isPresent()) {
			employeeRepository.delete(empDetails.get());
		} else {
			System.out.println("record does not exist with this id" + employeeId);

		}

	}

	public List<Employee> fetchEmployeesByIsActiveState() {
		List<Employee> ls = employeeRepository.findAll().stream().filter(emp -> emp.getIsActive())
				.collect(Collectors.toList());

		List<Employee> activeEmployees = new ArrayList<Employee>();
		for (Employee emp : ls) {
			if (emp.getIsActive()) {
				activeEmployees.add(emp);
			}
		}

		return ls;
	}

}
