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
import com.ensar.entity.HRStatus;
import com.ensar.request.dto.CreateUpdateHRStatusDto;
import com.ensar.response.dto.SuccessResponse;
import com.ensar.service.FileStorageService;
import com.ensar.service.HRStatusService;

import io.swagger.annotations.Api;

@Api(tags = "HRStatus Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/hrstatus")
public class HRStatusController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HRStatusService hrStatusService;

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<List<HRStatus>> getHRStatus() {
		return ResponseEntity.ok(hrStatusService.getAllHRStatus());
	}

	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<HRStatus> createHRStatus(
			@Valid @RequestBody CreateUpdateHRStatusDto createUpdateHRStatusDto) {
		return ResponseEntity.ok(hrStatusService.createOrUpdateHRStatus(Optional.empty(), createUpdateHRStatusDto));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<HRStatus> updateHRStatus(@PathVariable String id,
			@Valid @RequestBody CreateUpdateHRStatusDto createUpdateHRStatusDto) {
		return ResponseEntity.ok(hrStatusService.createOrUpdateHRStatus(Optional.of(id), createUpdateHRStatusDto));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<HRStatus> getHRStatus(@PathVariable String id) {
		return ResponseEntity.ok(hrStatusService.getHRStatusById(id));
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public String deleteUser(@PathVariable String id) {

		hrStatusService.delete(id);

		return id;
	}

}
