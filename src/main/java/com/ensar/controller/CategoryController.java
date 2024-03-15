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

import com.ensar.entity.Category;
import com.ensar.request.dto.CreateUpdateCategoryDto;
import com.ensar.service.CategoryService;

import io.swagger.annotations.Api;

@Api(tags = "Category")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/Category")
public class CategoryController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping("/")
	public ResponseEntity<Category> createupdateCategory(
			@Valid @RequestBody CreateUpdateCategoryDto createUpdateCategoryDto) {
		return ResponseEntity.ok(categoryService.createUpdateCategory(Optional.empty(), createUpdateCategoryDto));
	}

	@GetMapping("/")
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryService.getAllCategory());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable String id) {
		return ResponseEntity.ok(categoryService.getCategoryById(id));

	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable String id,
			@Valid @RequestBody CreateUpdateCategoryDto createUpdateCategoryDto) {
		return ResponseEntity.ok(categoryService.createUpdateCategory(Optional.of(id), createUpdateCategoryDto));
	}

	@DeleteMapping("/{id}")

	public String deleteCategory(@PathVariable String id) {

		categoryService.delete(id);

		return id;
	}

}
