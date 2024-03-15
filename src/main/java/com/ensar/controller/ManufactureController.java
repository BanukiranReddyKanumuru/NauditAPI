package com.ensar.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.ensar.entity.Manufacturer;
import com.ensar.request.dto.CreateUpdateManufactureDto;
import com.ensar.service.ManufactureService;

import io.swagger.annotations.Api;

@Api(tags = "Manufacture Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/manufacturer")
public class ManufactureController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private ManufactureService manufactureService;

	@Autowired
	public ManufactureController(ManufactureService manufactureService) {
		this.manufactureService = manufactureService;
	}

	@PostMapping("/")
	public ResponseEntity<Manufacturer> createupdateManufacturer(
			@Valid @RequestBody CreateUpdateManufactureDto createUpdateManufactureDto) {
		return ResponseEntity
				.ok(manufactureService.createOrUpdateManufacturer(Optional.empty(), createUpdateManufactureDto));
	}

	@GetMapping("/")
	public ResponseEntity<List<Manufacturer>> getAll() {
		return ResponseEntity.ok(manufactureService.getAllManufacturers());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Manufacturer> getManufacture(@PathVariable String id) {
		return ResponseEntity.ok(manufactureService.getManufactureById(id));

	}

	@PutMapping("/{id}")
	public ResponseEntity<Manufacturer> updateManufacture(@PathVariable String id,
			@Valid @RequestBody CreateUpdateManufactureDto createUpdateManufactureDto) {
				return ResponseEntity.ok(manufactureService.createOrUpdateManufacturer(Optional.of(id), createUpdateManufactureDto));

	}

	@DeleteMapping("{id}")
	public String deleteControls(@PathVariable String id) {

		LOGGER.debug("delete: tries to manufacture controls for the Id: {}", id);
		manufactureService.delete(id);

		return id;
	}
}
