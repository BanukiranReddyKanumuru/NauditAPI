package com.ensar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ensar.entity.Employees;
import com.ensar.service.EmployeeService;

import io.swagger.annotations.Api;

@Api(tags = "Employee Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/employee")
public class EmployeeController {
	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public EmployeeController() {
		super();
	}

	@PostMapping("/")
	public @ResponseBody ResponseEntity<String> createEmployee(@RequestBody Employees employee) {
		try {
			employeeService.createEmployee(employee);
		} catch (Exception e) {
			System.out.println("error");
		}
		return ResponseEntity.ok("Employee created");
	}
}