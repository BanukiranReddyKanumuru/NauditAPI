package com.ensar.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ensar.entity.FileDB;
import com.ensar.entity.Department;
import com.ensar.request.dto.CreateUpdateDepartmentDto;
import com.ensar.response.dto.SuccessResponse;
import com.ensar.service.FileStorageService;
import com.ensar.service.DepartmentService;

import io.swagger.annotations.Api;

@Api(tags = "Department Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/department")
public class DepartmentController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<List<Department>> getDepartment() {
		return ResponseEntity.ok(departmentService.getAllDepartment());
	}

	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Department> createDepartment(
			@Valid @RequestBody CreateUpdateDepartmentDto createUpdateDepartmentDto) {
		return ResponseEntity.ok(departmentService.createOrUpdateDepartment(Optional.empty(), createUpdateDepartmentDto));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Department> updateDepartment(@PathVariable String id,
			@Valid @RequestBody CreateUpdateDepartmentDto createUpdateDepartmentDto) {
		return ResponseEntity.ok(departmentService.createOrUpdateDepartment(Optional.of(id), createUpdateDepartmentDto));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Department> getDepartment(@PathVariable String id) {
		return ResponseEntity.ok(departmentService.getDepartmentById(id));
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public String deleteDepartment(@PathVariable String id) {

		departmentService.delete(id);

		return id;
	}

}
