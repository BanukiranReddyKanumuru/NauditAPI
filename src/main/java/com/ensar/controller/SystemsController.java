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
import com.ensar.entity.Systems;
import com.ensar.request.dto.CreateUpdateSystemsDto;
import com.ensar.response.dto.SuccessResponse;
import com.ensar.service.FileStorageService;
import com.ensar.service.SystemsService;


import io.swagger.annotations.Api;

@Api(tags = "Systems Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/systems")
public class SystemsController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private SystemsService systemsService;
	
	 @Autowired
	 private FileStorageService storageService;

	@Autowired
	public SystemsController(SystemsService systemsService) {
		this.systemsService = systemsService;
	}

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<List<Systems>> getSystems() {
		return ResponseEntity.ok(systemsService.getAllSystems());
	}

	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Systems> createSystems(@Valid @RequestBody CreateUpdateSystemsDto createUpdateSystemsDto) {
		return ResponseEntity.ok(systemsService.createOrUpdateSystems(Optional.empty(), createUpdateSystemsDto));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Systems> updateSystems(@PathVariable String id,
			@Valid @RequestBody CreateUpdateSystemsDto createUpdateSystemsDto) {
		return ResponseEntity.ok(systemsService.createOrUpdateSystems(Optional.of(id), createUpdateSystemsDto));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Systems> getSystems(@PathVariable String id) {
		return ResponseEntity.ok(systemsService.getSystemsById(id));
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public String deleteUser(@PathVariable String id) {

		systemsService.delete(id);

		return id;
	}
	
	 @GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
	    FileDB fileDB = storageService.getById(id);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
	        .body(fileDB.getData());
	  }
	  
	  @PostMapping("/files/{id}")
	  @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	  public ResponseEntity<FileDB> uploadImage(@PathVariable String id, 
			  @RequestParam("file") MultipartFile file) throws IOException {

		  return ResponseEntity.ok(systemsService.uploadImage(id, file));
		  	  }
	  
	  @PostMapping("/import")
	  @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	  public ResponseEntity<String> importFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    
	      try {
	    	  systemsService.csvToSystems(file.getInputStream());
	    	LOGGER.info("file uploaded");
	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.ok(message);
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.badRequest().body(message);
	      }
	  }

}
