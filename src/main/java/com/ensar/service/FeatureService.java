package com.ensar.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.Feature;
import com.ensar.entity.Software;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.FeatureRepository;
import com.ensar.repository.SoftwareRepository;
import com.ensar.request.dto.CreateUpdateFeatureDto;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional
public class FeatureService {

	private FeatureRepository featureRepository;

	private SoftwareRepository softwareRepository;

	@Autowired
	public FeatureService(FeatureRepository featureRepositoryy, SoftwareRepository softwareRepository) {
		this.featureRepository = featureRepositoryy;
		this.softwareRepository = softwareRepository;
	}

	public Feature createUpdateFeatureDto(@Valid CreateUpdateFeatureDto createUpdateFeatureDto) {

		Feature feature = new Feature();

		feature.setName(createUpdateFeatureDto.getName());
		feature.setHostlocation(createUpdateFeatureDto.getHostLocation());
		feature.setOwner(createUpdateFeatureDto.getOwner());
		Software software = softwareRepository.findById(createUpdateFeatureDto.getSoftwareid()).get();
		feature.setSoftware(software);
//		feature.setLastReviewed_date(createUpdateFeatureDto.getLastreviewed_date());
		return featureRepository.save(feature);
	}

	public List<Feature> getAllFeature() {

		return featureRepository.findAll();
	}

	public Feature getfeatureById(String id) {
		Optional<Feature> featureOptional = featureRepository.findById(id);

		if (!featureOptional.isPresent())
			throw new RuntimeException("feature with " + id + " not found.");
		return featureOptional.get();
	}

	public Feature createOrUpdateFeature(Optional<String> feature_id,
			@Valid CreateUpdateFeatureDto createUpdateFeatureDto) {
		Feature feature;
		if (feature_id.isPresent()) {
			feature = featureRepository.getById(feature_id.get());
			if (feature == null)
				throw new RuntimeException("feature with id " + feature_id.get() + " not found");
		} else {
			feature = new Feature();
			if (featureRepository.existsByName(createUpdateFeatureDto.getName()))
				throw new RuntimeException(
						"Feature with name " + createUpdateFeatureDto.getName() + " already exists.");
		}

		boolean softwareexists = softwareRepository.existsById(createUpdateFeatureDto.getSoftwareid());

		if (!softwareexists)
			throw new RuntimeException("software with id " + createUpdateFeatureDto.getSoftwareid() + " not exists.");

		feature.setName(createUpdateFeatureDto.getName());
		feature.setHostlocation(createUpdateFeatureDto.getHostLocation());
		feature.setOwner(createUpdateFeatureDto.getOwner());
		feature.setReviewcycle(createUpdateFeatureDto.getReviewCycle());
		feature.setSoftware(softwareRepository.findById(createUpdateFeatureDto.getSoftwareid()).get());

		return featureRepository.save(feature);
	}

	public void delete(final String id) {

		Optional<Feature> FeatureEntity = featureRepository.findById(id);

		if (FeatureEntity.isEmpty())
			throw new InvalidInputException("Invalid Feature id: " + id);

		featureRepository.deleteById(id);
	}

}
