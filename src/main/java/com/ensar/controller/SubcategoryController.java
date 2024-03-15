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

import com.ensar.entity.Subcategory;
import com.ensar.request.dto.CreateUpdateSubcategoryDto;
import com.ensar.service.SubcategoryService;

import io.swagger.annotations.Api;

@Api(tags = "subcategory")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/subcategory")
public class SubcategoryController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private SubcategoryService subcategoryService;

	@Autowired
	public SubcategoryController(SubcategoryService subcategoryService) {
		this.subcategoryService = subcategoryService;
	}

	@PostMapping("/")
	public ResponseEntity<Subcategory> createupdateSubcategory(
			@Valid @RequestBody CreateUpdateSubcategoryDto createUpdateSubcategoryDto) {
		return ResponseEntity
				.ok(subcategoryService.createOrUpdateSubcategory(Optional.empty(), createUpdateSubcategoryDto));
	}

	@GetMapping("/")
	public ResponseEntity<List<Subcategory>> getAll() {
		return ResponseEntity.ok(subcategoryService.getAllSubcategory());
	}

	@GetMapping("/{id}")

	public ResponseEntity<Subcategory> getsubcategory(@PathVariable String id) {
		return ResponseEntity.ok(subcategoryService.getSubcategoryById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Subcategory> updateSubcategory(@PathVariable String id,
			@Valid @RequestBody CreateUpdateSubcategoryDto createUpdateSubcategoryDto) {
		return ResponseEntity
				.ok(subcategoryService.createOrUpdateSubcategory(Optional.of(id), createUpdateSubcategoryDto));
	}

	@DeleteMapping("{id}")
	public String deleteSubcategory(@PathVariable String id) {

		subcategoryService.delete(id);

		return id;
	}

}
