package com.ensar.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.Controls;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.ControlsRepository;
import com.ensar.repository.SubcategoryRepository;
import com.ensar.request.dto.CreateUpdateControlsDto;

@Service
public class ControlsService {

	private ControlsRepository controlsRepository;
	private SubcategoryRepository subcategoryRepository;

	@Autowired
	public ControlsService(ControlsRepository controlsRepository, SubcategoryRepository subcategoryRepository) {
		this.controlsRepository = controlsRepository;
		this.subcategoryRepository = subcategoryRepository;
	}

	public List<Controls> getAllControls() {
		return controlsRepository.findAll();
	}

	public Controls getControlsById(String id) {
		Optional<Controls> ControlsOptional = controlsRepository.findById(id);

		if (!ControlsOptional.isPresent())
			throw new RuntimeException("Controls with " + id + " not found.");
		return ControlsOptional.get();
	}

	public Controls createorUpdateControls(Optional<String> controls_id,
			@Valid CreateUpdateControlsDto createUpdateControlsDto) {
		Controls controls;
		if (controls_id.isPresent()) {
			controls = controlsRepository.getById(controls_id.get());
			if (controls == null)
				throw new RuntimeException("controls with id " + controls_id.get() + " not found");
		} else {
			controls = new Controls();
			if (controlsRepository.existsByName(createUpdateControlsDto.getName()))
				throw new RuntimeException(
						"Control with name " + createUpdateControlsDto.getName() + " already exists.");
		}

		boolean subcategoryexists = subcategoryRepository.existsById(createUpdateControlsDto.getSubcategory());

		if (!subcategoryexists)
			throw new RuntimeException(
					"subcategory with id " + createUpdateControlsDto.getSubcategory() + " not exists.");

		controls.setName(createUpdateControlsDto.getName());
		controls.setSubcategoryid(subcategoryRepository.findById(createUpdateControlsDto.getSubcategory()).get());

		return controlsRepository.save(controls);
	}

	public void delete(final String id) {

		Optional<Controls> ControlsEntity = controlsRepository.findById(id);

		if (ControlsEntity.isEmpty())
			throw new InvalidInputException("Invalid Controls id: " + id);

		controlsRepository.deleteById(id);
	}

}
