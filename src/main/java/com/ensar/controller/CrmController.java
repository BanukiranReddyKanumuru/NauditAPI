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
import com.ensar.entity.LinkedinAccount;
import com.ensar.entity.Crm;
import com.ensar.request.dto.CreateUpdateCrmDto;
import com.ensar.request.dto.CreateUpdateLinkedinAccountDto;
import com.ensar.response.dto.SuccessResponse;
import com.ensar.service.FileStorageService;
import com.ensar.service.CrmService;

import io.swagger.annotations.Api;

@Api(tags = "CRM Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/crm")
public class CrmController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CrmService crmService;

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<List<Crm>> getCrm() {
		return ResponseEntity.ok(crmService.getAllCrm());
	}

	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Crm> createCrm(
			@Valid @RequestBody CreateUpdateCrmDto createUpdateCrmDto) {
		return ResponseEntity.ok(crmService.createOrUpdateCrm(Optional.empty(), createUpdateCrmDto));
	}
	


	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Crm> updateCrm(@PathVariable String id,
			@Valid @RequestBody CreateUpdateCrmDto createUpdateCrmDto) {
		return ResponseEntity.ok(crmService.createOrUpdateCrm(Optional.of(id), createUpdateCrmDto));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Crm> getCrm(@PathVariable String id) {
		return ResponseEntity.ok(crmService.getCrmById(id));
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public String deleteCrm(@PathVariable String id) {

		crmService.delete(id);

		return id;
	}

}
