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
import com.ensar.entity.LinkedinLead;
import com.ensar.request.dto.CreateUpdateLinkedinLeadDto;
import com.ensar.response.dto.SuccessResponse;
import com.ensar.service.FileStorageService;
import com.ensar.service.LinkedinLeadService;

import io.swagger.annotations.Api;

@Api(tags = "LinkedinLead Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/linkedinLead")
public class LinkedinLeadController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LinkedinLeadService linkedinLeadService;

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<List<LinkedinLead>> getLinkedinLead() {
		return ResponseEntity.ok(linkedinLeadService.getAllLinkedinLead());
	}

	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<LinkedinLead> createLinkedinLead(
			@Valid @RequestBody CreateUpdateLinkedinLeadDto createUpdateLinkedinLeadDto) {
		return ResponseEntity.ok(linkedinLeadService.createOrUpdateLinkedinLead(Optional.empty(), createUpdateLinkedinLeadDto));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<LinkedinLead> updateLinkedinLead(@PathVariable String id,
			@Valid @RequestBody CreateUpdateLinkedinLeadDto createUpdateLinkedinLeadDto) {
		return ResponseEntity.ok(linkedinLeadService.createOrUpdateLinkedinLead(Optional.of(id), createUpdateLinkedinLeadDto));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<LinkedinLead> getLinkedinLead(@PathVariable String id) {
		return ResponseEntity.ok(linkedinLeadService.getLinkedinLeadById(id));
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public String deleteLinkedinLead(@PathVariable String id) {

		linkedinLeadService.delete(id);

		return id;
	}

}
