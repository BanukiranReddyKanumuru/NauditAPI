package com.ensar.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.Crm;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.CrmRepository;
import com.ensar.repository.UserRepository;
import com.ensar.request.dto.CreateUpdateCrmDto;

@Service
public class CrmService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CrmRepository crmRepository;

	@Autowired
	private UserRepository userRepository;

	public Crm getCrmById(String id) {
		Optional<Crm> crmOptional = crmRepository.findById(id);

		if (!crmOptional.isPresent())
			throw new RuntimeException("Crm with " + id + " not found.");
		return crmOptional.get();
	}

	public List<Crm> getAllCrm() {
		List<Crm> list = crmRepository.findAll();

		return list;
	}

	@Transactional
	public Crm createOrUpdateCrm(Optional<String> crmId,
			CreateUpdateCrmDto createUpdateCrmDto) {
		Crm crm;

		// if id present, update record. Verify that id exist in database
		if (crmId.isPresent()) {
			crm = crmRepository.getById(crmId.get());
			LOGGER.info("Updating CRM record:" + crmId.get());
			if (crm == null)
				throw new RuntimeException("LinkedinAccount with id " + crmId.get() + " not found");
		} else {
			crm = new Crm();
			LOGGER.info("Creating new CRM record");
		}


		// set email/ firstname/ password / type

		if (!createUpdateCrmDto.getEmail().isEmpty())
			crm.setEmail(createUpdateCrmDto.getEmail());
		crm.setFirstName(createUpdateCrmDto.getFirstName());
		crm.setLastName(createUpdateCrmDto.getLastName());
		crm.setPassword(createUpdateCrmDto.getPassword());
		crm.setJoinDate(createUpdateCrmDto.getJoinDate());
		crm.setType(createUpdateCrmDto.getType());

		crm = crmRepository.save(crm);
		LOGGER.info("Crated/Updated Crm record:" + crm.getId());
		return crm;
	}

	public void delete(final String id) {

		Optional<Crm> crmEntity = crmRepository.findById(id);

		if (crmEntity.isEmpty())
			throw new InvalidInputException("Invalid CRM id: " + id);

		crmRepository.deleteById(id);
	}

}
