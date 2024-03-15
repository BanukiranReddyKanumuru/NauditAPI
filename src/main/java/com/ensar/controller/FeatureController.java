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

import com.ensar.entity.Feature;
import com.ensar.request.dto.CreateUpdateFeatureDto;
import com.ensar.service.FeatureService;

import io.swagger.annotations.Api;

@Api(tags = "feature")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/featuree")
public class FeatureController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private FeatureService featureService;

	@Autowired
	public FeatureController(FeatureService fetureService) {
		this.featureService = fetureService;
	}

	@PostMapping("/")
	public ResponseEntity<Feature> createupdateFeature(
			@Valid @RequestBody CreateUpdateFeatureDto createUpdateFeatureDto) {
		return ResponseEntity.ok(featureService.createOrUpdateFeature(Optional.empty(), createUpdateFeatureDto));

	}

	@GetMapping("/")
	public ResponseEntity<List<Feature>> getAll() {
		return ResponseEntity.ok(featureService.getAllFeature());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Feature> getFeature(@PathVariable String id) {
		return ResponseEntity.ok(featureService.getfeatureById(id));

	}

	@PutMapping("/{id}")
	public ResponseEntity<Feature> updateFeature(@PathVariable String id,
			@Valid @RequestBody CreateUpdateFeatureDto createUpdateFeatureDto) {
		return ResponseEntity.ok(featureService.createOrUpdateFeature(Optional.of(id), createUpdateFeatureDto));
	}

	@DeleteMapping("{id}")
	public String deleteControls(@PathVariable String id) {

		LOGGER.debug("delete: tries to feature controls for the Id: {}", id);
		featureService.delete(id);

		return id;
	}

}
