package com.ensar.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.Department;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.DepartmentRepository;
import com.ensar.request.dto.CreateUpdateDepartmentDto;


@Service
public class DepartmentService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DepartmentRepository departmentRepository;



	public Department getDepartmentById(String id) {
		Optional<Department> departmentOptional = departmentRepository.findById(id);

		if (!departmentOptional.isPresent())
			throw new RuntimeException("Department with " + id + " not found.");
		return departmentOptional.get();
	}

	public List<Department> getAllDepartment() {
		List<Department> list = departmentRepository.findAll();

		return list;
	}

	@Transactional
	public Department createOrUpdateDepartment(Optional<String> departmentId,
			CreateUpdateDepartmentDto createUpdateDepartmentDto) {
		Department department;

		// if id present, update record. Verify that id exist in database
		if (departmentId.isPresent()) {
			department= departmentRepository.getById(departmentId.get());
			LOGGER.info("Updating Department record:" + departmentId.get());
			if (department == null)
				throw new RuntimeException("Department with id " + departmentId.get() + " not found");
		} else {
			department = new Department();
			LOGGER.info("Creating new Department record");
		}

		
		// set name
		
		if(!createUpdateDepartmentDto.getName().isEmpty())
			department.setName(createUpdateDepartmentDto.getName());

		
		department = departmentRepository.save(department);
		LOGGER.info("Crated/Updated Department record:" + department.getId());
		return department;
	}

	public void delete(final String id) {

		Optional<Department> departmentEntity = departmentRepository.findById(id);

		if (departmentEntity.isEmpty())
			throw new InvalidInputException("Invalid department id: " + id);

		departmentRepository.deleteById(id);
	}

}
