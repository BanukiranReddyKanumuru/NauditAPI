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
import com.ensar.entity.HRTask;
import com.ensar.request.dto.CreateUpdateHRTaskDto;
import com.ensar.response.dto.SuccessResponse;
import com.ensar.service.FileStorageService;
import com.ensar.service.HRTaskService;

import io.swagger.annotations.Api;

@Api(tags = "HRTask Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/hrtask")
public class HRTaskController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HRTaskService hrTaskService;

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<List<HRTask>> getHRTask() {
		return ResponseEntity.ok(hrTaskService.getAllHRTask());
	}

	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<HRTask> createHRTask(
			@Valid @RequestBody CreateUpdateHRTaskDto createUpdateHRTaskDto) {
		return ResponseEntity.ok(hrTaskService.createOrUpdateHRTask(Optional.empty(), createUpdateHRTaskDto));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<HRTask> updateHRTask(@PathVariable String id,
			@Valid @RequestBody CreateUpdateHRTaskDto createUpdateHRTaskDto) {
		return ResponseEntity.ok(hrTaskService.createOrUpdateHRTask(Optional.of(id), createUpdateHRTaskDto));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<HRTask> getHRTask(@PathVariable String id) {
		return ResponseEntity.ok(hrTaskService.getHRTaskById(id));
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public String deleteUser(@PathVariable String id) {

		hrTaskService.delete(id);

		return id;
	}

}
