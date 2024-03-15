package com.ensar.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.Category;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.CategoryRepository;
import com.ensar.repository.FeatureRepository;
import com.ensar.request.dto.CreateUpdateCategoryDto;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private FeatureRepository featureRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepositoryy, FeatureRepository featureRepository) {
		this.categoryRepository = categoryRepositoryy;
		this.featureRepository = featureRepository;
	}

	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	public Category getCategoryById(String id) {
		Optional<Category> CategoryOptional = categoryRepository.findById(id);

		if (!CategoryOptional.isPresent())
			throw new RuntimeException("Category with " + id + " not found.");
		return CategoryOptional.get();
	}

	public Category createUpdateCategory(Optional<String> category_id,
			@Valid CreateUpdateCategoryDto createUpdateCategoryDto) {
		Category category;
		if (category_id.isPresent()) {
			category = categoryRepository.getById(category_id.get());
			if (category == null)
				throw new RuntimeException("Category with id " + category_id.get() + " not found");
		} else {
			category = new Category();
			if (categoryRepository.existsByName(createUpdateCategoryDto.getName()))
				throw new RuntimeException(
						"Category with name " + createUpdateCategoryDto.getName() + " already exists.");
		}
		boolean featureexists = featureRepository.existsById(createUpdateCategoryDto.getFeatureid());

		if (!featureexists)
			throw new RuntimeException("feature with id " + createUpdateCategoryDto.getFeatureid() + " not exists.");

		category.setName(createUpdateCategoryDto.getName());
		category.setFeature(featureRepository.findById(createUpdateCategoryDto.getFeatureid()).get());

		return categoryRepository.save(category);
	}

	public void delete(final String id) {

		Optional<Category> categoryEntity = categoryRepository.findById(id);

		if (categoryEntity.isEmpty())
			throw new InvalidInputException("Invalid Category id: " + id);

		categoryRepository.deleteById(id);
	}
}
