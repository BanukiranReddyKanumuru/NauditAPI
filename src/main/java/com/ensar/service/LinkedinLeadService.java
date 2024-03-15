package com.ensar.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.LinkedinLead;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.LinkedinLeadRepository;
import com.ensar.repository.UserRepository;
import com.ensar.request.dto.CreateUpdateLinkedinLeadDto;


@Service
public class LinkedinLeadService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LinkedinLeadRepository linkedinLeadRepository;


	@Autowired
	private UserRepository userRepository;

	public LinkedinLead getLinkedinLeadById(String id) {
		Optional<LinkedinLead> linkedinLeadOptional = linkedinLeadRepository.findById(id);

		if (!linkedinLeadOptional.isPresent())
			throw new RuntimeException("LinkedinLead with " + id + " not found.");
		return linkedinLeadOptional.get();
	}

	public List<LinkedinLead> getAllLinkedinLead() {
		List<LinkedinLead> list = linkedinLeadRepository.findAll();

		return list;
	}

	@Transactional
	public LinkedinLead createOrUpdateLinkedinLead(Optional<String> linkedinLeadId,
			CreateUpdateLinkedinLeadDto createUpdateLinkedinLeadDto) {
		LinkedinLead linkedinLead;

		// if id present, update record. Verify that id exist in database
		if (linkedinLeadId.isPresent()) {
			linkedinLead= linkedinLeadRepository.getById(linkedinLeadId.get());
			LOGGER.info("Updating LinkedinLead record:" + linkedinLeadId.get());
			if (linkedinLead == null)
				throw new RuntimeException("LinkedinLead with id " + linkedinLeadId.get() + " not found");
		} else {
			linkedinLead = new LinkedinLead();
			LOGGER.info("Creating new LinkedinLead record");
		}

		// set user
		if (createUpdateLinkedinLeadDto.getUserId() != null
				&& !createUpdateLinkedinLeadDto.getUserId().isEmpty()) {
			boolean userexists = userRepository.existsById(createUpdateLinkedinLeadDto.getUserId());

			if (!userexists) {
				throw new RuntimeException(
						"user with id " + createUpdateLinkedinLeadDto.getUserId() + " not exists.");
			} else {
				linkedinLead.setUser(userRepository.findById(createUpdateLinkedinLeadDto.getUserId()).get());
			}
		}
		// set website_link, lead_name, linkedin_link, response_type, sent
		
		if(!createUpdateLinkedinLeadDto.getWebsiteLink().isEmpty())
			linkedinLead.setWebsiteLink(createUpdateLinkedinLeadDto.getWebsiteLink());
		linkedinLead.setLeadName(createUpdateLinkedinLeadDto.getLeadName());
		linkedinLead.setLinkedinLink(createUpdateLinkedinLeadDto.getLinkedinLink());
		linkedinLead.setResponseType(createUpdateLinkedinLeadDto.getResponseType());
		linkedinLead.setSent(createUpdateLinkedinLeadDto.getSent());
		
		linkedinLead = linkedinLeadRepository.save(linkedinLead);
		LOGGER.info("Crated/Updated LinkedinLead record:" + linkedinLead.getId());
		return linkedinLead;
	}

	public void delete(final String id) {

		Optional<LinkedinLead> linkedinLeadEntity = linkedinLeadRepository.findById(id);

		if (linkedinLeadEntity.isEmpty())
			throw new InvalidInputException("Invalid LinkedinLead id: " + id);

		linkedinLeadRepository.deleteById(id);
	}

}
