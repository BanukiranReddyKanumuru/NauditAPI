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
import com.ensar.entity.HRProject;
import com.ensar.request.dto.CreateUpdateHRProjectDto;
import com.ensar.response.dto.SuccessResponse;
import com.ensar.service.FileStorageService;
import com.ensar.service.HRProjectService;

import io.swagger.annotations.Api;

@Api(tags = "HRProject Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/hrproject")
public class HRProjectController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HRProjectService hrProjectService;

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<List<HRProject>> getHRProject() {
		return ResponseEntity.ok(hrProjectService.getAllHRProject());
	}

	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<HRProject> createHRProject(
			@Valid @RequestBody CreateUpdateHRProjectDto createUpdateHRProjectDto) {
		return ResponseEntity.ok(hrProjectService.createOrUpdateHRProject(Optional.empty(), createUpdateHRProjectDto));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<HRProject> updateHRProject(@PathVariable String id,
			@Valid @RequestBody CreateUpdateHRProjectDto createUpdateHRProjectDto) {
		return ResponseEntity.ok(hrProjectService.createOrUpdateHRProject(Optional.of(id), createUpdateHRProjectDto));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<HRProject> getHRProject(@PathVariable String id) {
		return ResponseEntity.ok(hrProjectService.getHRProjectById(id));
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public String deleteUser(@PathVariable String id) {

		hrProjectService.delete(id);

		return id;
	}

}
