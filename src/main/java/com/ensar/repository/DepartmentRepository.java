package com.ensar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

	

   
}
