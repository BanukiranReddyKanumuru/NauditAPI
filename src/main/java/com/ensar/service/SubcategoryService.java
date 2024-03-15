package com.ensar.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.Subcategory;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.CategoryRepository;
import com.ensar.repository.SubcategoryRepository;
import com.ensar.request.dto.CreateUpdateSubcategoryDto;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional
public class SubcategoryService {

	@Autowired
	private SubcategoryRepository subcategoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	public SubcategoryService(SubcategoryRepository subcategoryRepositoryy, CategoryRepository categoryRepository) {
		this.subcategoryRepository = subcategoryRepositoryy;
		this.categoryRepository = categoryRepository;
	}

	public List<Subcategory> getAllSubcategory() {
		// TODO Auto-generated method stub
		return subcategoryRepository.findAll();
	}

	public Subcategory getSubcategoryById(String id) {
		Optional<Subcategory> SubcategoryOptional = subcategoryRepository.findById(id);

		if (!SubcategoryOptional.isPresent())
			throw new RuntimeException("SubcategoryOptional with " + id + " not found.");
		return SubcategoryOptional.get();
	}

	public Subcategory createOrUpdateSubcategory(Optional<String> subcategory_id,
			@Valid CreateUpdateSubcategoryDto createUpdateSubcategoryDto) {
		Subcategory subcategory;
		if (subcategory_id.isPresent()) {
			subcategory = subcategoryRepository.getById(subcategory_id.get());
			if (subcategory == null)
				throw new RuntimeException("Category with id " + subcategory_id.get() + " not found");
		} else {
			subcategory = new Subcategory();
			if (subcategoryRepository.existsByName(createUpdateSubcategoryDto.getName()))
				throw new RuntimeException(
						"subcategory with name " + createUpdateSubcategoryDto.getName() + " already exists.");
		}

		boolean categoryexists = categoryRepository.existsById(createUpdateSubcategoryDto.getCategoryid());

		if (!categoryexists)
			throw new RuntimeException(
					"Category with id " + createUpdateSubcategoryDto.getCategoryid() + " not exists.");

		subcategory.setName(createUpdateSubcategoryDto.getName());
		subcategory.setCategory(categoryRepository.findById(createUpdateSubcategoryDto.getCategoryid()).get());

		return subcategoryRepository.save(subcategory);
	}

	public void delete(final String id) {

		Optional<Subcategory> SubcategoryEntity = subcategoryRepository.findById(id);

		if (SubcategoryEntity.isEmpty())
			throw new InvalidInputException("Invalid Subcategory id: " + id);

		subcategoryRepository.deleteById(id);
	}

}
