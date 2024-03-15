package com.ensar.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.LinkedinAccount;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.LinkedinAccountRepository;
import com.ensar.repository.UserRepository;
import com.ensar.request.dto.CreateUpdateLinkedinAccountDto;

@Service
public class LinkedinAccountService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LinkedinAccountRepository linkedinAccountRepository;

	@Autowired
	private UserRepository userRepository;

	public LinkedinAccount getLinkedinAccountById(String id) {
		Optional<LinkedinAccount> linkedinAccountOptional = linkedinAccountRepository.findById(id);

		if (!linkedinAccountOptional.isPresent())
			throw new RuntimeException("LinkedinAccount with " + id + " not found.");
		return linkedinAccountOptional.get();
	}

	public List<LinkedinAccount> getAllLinkedinAccount() {
		List<LinkedinAccount> list = linkedinAccountRepository.findAll();

		return list;
	}

	@Transactional
	public LinkedinAccount createOrUpdateLinkedinAccount(Optional<String> linkedinAccountId,
			CreateUpdateLinkedinAccountDto createUpdateLinkedinAccountDto) {
		LinkedinAccount linkedinAccount;

		// if id present, update record. Verify that id exist in database
		if (linkedinAccountId.isPresent()) {
			linkedinAccount = linkedinAccountRepository.getById(linkedinAccountId.get());
			LOGGER.info("Updating LinkedinAccount record:" + linkedinAccountId.get());
			if (linkedinAccount == null)
				throw new RuntimeException("LinkedinAccount with id " + linkedinAccountId.get() + " not found");
		} else {
			linkedinAccount = new LinkedinAccount();
			LOGGER.info("Creating new LinkedinAccount record");
		}

		// set user
		if (createUpdateLinkedinAccountDto.getUserId() != null
				&& !createUpdateLinkedinAccountDto.getUserId().isEmpty()) {
			boolean userexists = userRepository.existsById(createUpdateLinkedinAccountDto.getUserId());

			if (!userexists) {
				throw new RuntimeException(
						"user with id " + createUpdateLinkedinAccountDto.getUserId() + " not exists.");
			} else {
				linkedinAccount.setUser(userRepository.findById(createUpdateLinkedinAccountDto.getUserId()).get());
			}
		}

		// set email/ firstname/ password / type

		if (!createUpdateLinkedinAccountDto.getEmail().isEmpty())
			linkedinAccount.setEmail(createUpdateLinkedinAccountDto.getEmail());
		linkedinAccount.setFirstName(createUpdateLinkedinAccountDto.getFirstName());
		linkedinAccount.setLastName(createUpdateLinkedinAccountDto.getLastName());
		linkedinAccount.setPassword(createUpdateLinkedinAccountDto.getPassword());
		linkedinAccount.setType(createUpdateLinkedinAccountDto.getType());

		linkedinAccount = linkedinAccountRepository.save(linkedinAccount);
		LOGGER.info("Crated/Updated LinkedinAccount record:" + linkedinAccount.getId());
		return linkedinAccount;
	}

	public void delete(final String id) {

		Optional<LinkedinAccount> linkedinAccountEntity = linkedinAccountRepository.findById(id);

		if (linkedinAccountEntity.isEmpty())
			throw new InvalidInputException("Invalid LinkedinAccount id: " + id);

		linkedinAccountRepository.deleteById(id);
	}

}
