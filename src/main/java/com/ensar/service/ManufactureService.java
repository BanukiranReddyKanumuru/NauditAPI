package com.ensar.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.Manufacturer;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.ManufactureRepository;
import com.ensar.request.dto.CreateUpdateManufactureDto;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional
public class ManufactureService {

	private ManufactureRepository manufactureRepository;

	@Autowired
	public ManufactureService(ManufactureRepository manufactureRepositoryy) {
		this.manufactureRepository = manufactureRepositoryy;
	}

	public List<Manufacturer> getAllManufacturers() {
		// TODO Auto-generated method stub
		return manufactureRepository.findAll();
	}

	public Manufacturer getManufactureById(String id) {
		Optional<Manufacturer> manufactureOptional = manufactureRepository.findById(id);

		if (!manufactureOptional.isPresent())
			throw new RuntimeException("manufacture with " + id + " not found.");
		return manufactureOptional.get();
	}

	public Manufacturer createOrUpdateManufacturer(Optional<String> manufacturer_id,
			@Valid CreateUpdateManufactureDto createUpdateManufactureDto) {
		// TODO Auto-generated method stub
		Manufacturer manufacturer;
		if (manufacturer_id.isPresent()) {
			manufacturer = manufactureRepository.getById(manufacturer_id.get());
			if (manufacturer == null)
				throw new RuntimeException("Category with id " + manufacturer_id.get() + " not found");
		} else {
			manufacturer = new Manufacturer();
			if (manufactureRepository.existsByName(createUpdateManufactureDto.getName()))
				throw new RuntimeException(
						"Category with name " + createUpdateManufactureDto.getName() + " already exists.");
		}

		manufacturer.setName(createUpdateManufactureDto.getName());

		return manufactureRepository.save(manufacturer);
	}

	public void delete(final String id) {

		Optional<Manufacturer> CategoryEntity = manufactureRepository.findById(id);

		if (CategoryEntity.isEmpty())
			throw new InvalidInputException("Invalid Manufacture id: " + id);

		manufactureRepository.deleteById(id);
	}

}
