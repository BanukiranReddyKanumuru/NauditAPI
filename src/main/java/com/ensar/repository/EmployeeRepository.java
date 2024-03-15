package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.Employees;

@Repository

public interface EmployeeRepository extends JpaRepository<Employees, String> {

}