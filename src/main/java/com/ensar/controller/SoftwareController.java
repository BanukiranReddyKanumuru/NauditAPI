package com.ensar.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.ensar.entity.Software;
import com.ensar.request.dto.CreateUpdateSoftwareDto;
import com.ensar.service.SoftwareService;

import io.swagger.annotations.Api;

@Api(tags = "Software Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/software")
public class SoftwareController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private SoftwareService softwareService;

	@Autowired
	public SoftwareController(SoftwareService softwareService) {
		this.softwareService = softwareService;
	}

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<List<Software>> getSoftwares() {
		return ResponseEntity.ok(softwareService.getAllSoftwares());
	}

	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Software> createSoftware(
			@Valid @RequestBody CreateUpdateSoftwareDto createUpdateSoftwareDto) {
		return ResponseEntity.ok(softwareService.createOrUpdateSoftware(Optional.empty(), createUpdateSoftwareDto));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Software> updateSoftware(@PathVariable String id,
			@Valid @RequestBody CreateUpdateSoftwareDto createUpdateSoftwareDto) {
		return ResponseEntity.ok(softwareService.createOrUpdateSoftware(Optional.of(id), createUpdateSoftwareDto));

	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Software> getSoftware(@PathVariable String id) {
		return ResponseEntity.ok(softwareService.getSoftwareById(id));

	}

	@DeleteMapping("{id}")
	public String deleteControls(@PathVariable String id) {

		softwareService.delete(id);

		return id;
	}

}
