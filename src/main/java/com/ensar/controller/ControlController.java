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

import com.ensar.entity.Controls;
import com.ensar.request.dto.CreateUpdateControlsDto;
import com.ensar.service.ControlsService;

import io.swagger.annotations.Api;

@Api(tags = "Controls Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/controls")
public class ControlController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private ControlsService controlsService;

	@Autowired
	public ControlController(ControlsService controlsService) {
		this.controlsService = controlsService;
	}

	@PostMapping("/")
	public ResponseEntity<Controls> createupdateControls(
			@Valid @RequestBody CreateUpdateControlsDto createUpdateControlsDto) {
		return ResponseEntity.ok(controlsService.createorUpdateControls(Optional.empty(), createUpdateControlsDto));
	}

	@GetMapping("/")
	public ResponseEntity<List<Controls>> getAll() {
		return ResponseEntity.ok(controlsService.getAllControls());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Controls> getControls(@PathVariable String id) {
		return ResponseEntity.ok(controlsService.getControlsById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Controls> updateControls(@PathVariable String id,
			@Valid @RequestBody CreateUpdateControlsDto createUpdateControlsDto) {
		return ResponseEntity.ok(controlsService.createorUpdateControls(Optional.of(id), createUpdateControlsDto));

	}

	@DeleteMapping("{id}")

	public String deleteControls(@PathVariable String id) {

		controlsService.delete(id);

		return id;
	}

}
