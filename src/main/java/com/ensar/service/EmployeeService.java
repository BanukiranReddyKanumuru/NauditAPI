package com.ensar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensar.entity.Employees;
import com.ensar.repository.EmployeeRepository;

@Service

@Transactional

public class EmployeeService {

 

private EmployeeRepository employeeRepository;

 

@Autowired

public EmployeeService(EmployeeRepository employeeRepository) {

this.employeeRepository = employeeRepository;

}

 

public EmployeeService() {

super();

}

 

public void createEmployee(Employees employee) {

employeeRepository.save(employee);

}

}