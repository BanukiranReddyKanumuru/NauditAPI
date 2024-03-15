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
import com.ensar.request.dto.CreateUpdateLinkedinAccountDto;
import com.ensar.response.dto.SuccessResponse;
import com.ensar.service.FileStorageService;
import com.ensar.service.LinkedinAccountService;

import io.swagger.annotations.Api;

@Api(tags = "LinkedinAccount Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/linkedinAccount")
public class LinkedinAccountController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LinkedinAccountService linkedinAccountService;

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<List<LinkedinAccount>> getLinkedinAccount() {
		return ResponseEntity.ok(linkedinAccountService.getAllLinkedinAccount());
	}

	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<LinkedinAccount> createLinkedinAccount(
			@Valid @RequestBody CreateUpdateLinkedinAccountDto createUpdateLinkedinAccountDto) {
		return ResponseEntity.ok(linkedinAccountService.createOrUpdateLinkedinAccount(Optional.empty(), createUpdateLinkedinAccountDto));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<LinkedinAccount> updateLinkedinAccount(@PathVariable String id,
			@Valid @RequestBody CreateUpdateLinkedinAccountDto createUpdateLinkedinAccountDto) {
		return ResponseEntity.ok(linkedinAccountService.createOrUpdateLinkedinAccount(Optional.of(id), createUpdateLinkedinAccountDto));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<LinkedinAccount> getLinkedinAccount(@PathVariable String id) {
		return ResponseEntity.ok(linkedinAccountService.getLinkedinAccountById(id));
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public String deleteLinkedinAccount(@PathVariable String id) {

		linkedinAccountService.delete(id);

		return id;
	}

}
