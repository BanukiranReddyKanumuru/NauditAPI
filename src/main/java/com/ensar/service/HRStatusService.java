package com.ensar.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ensar.entity.HRStatus;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.HRStatusRepository;
import com.ensar.repository.UserRepository;
import com.ensar.request.dto.CreateUpdateHRStatusDto;

@Service
public class HRStatusService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HRStatusRepository hrStatusRepository;

	@Autowired
	private UserRepository userRepository;

	public HRStatus getHRStatusById(String id) {
		Optional<HRStatus> hrStatusOptional = hrStatusRepository.findById(id);

		if (!hrStatusOptional.isPresent())
			throw new RuntimeException("HRStatus with " + id + " not found.");
		return hrStatusOptional.get();
	}

	public List<HRStatus> getAllHRStatus() {
		List<HRStatus> list = hrStatusRepository.findAll();

		return list;
	}

	@Transactional
	public HRStatus createOrUpdateHRStatus(Optional<String> hrStatusId,
			CreateUpdateHRStatusDto createUpdateHRStatusDto) {
		HRStatus hrStatus;

		// if id present, update record. Verify that id exist in database
		if (hrStatusId.isPresent()) {
			hrStatus = hrStatusRepository.getById(hrStatusId.get());
			LOGGER.info("Updating HRStatus record:" + hrStatusId.get());
			if (hrStatus == null)
				throw new RuntimeException("HRStatus with id " + hrStatusId.get() + " not found");
		} else {
			hrStatus = new HRStatus();
			LOGGER.info("Creating new HRStatus record");
		}

		// set user
		if (createUpdateHRStatusDto.getUserId() != null && !createUpdateHRStatusDto.getUserId().isEmpty()) {
			boolean userexists = userRepository.existsById(createUpdateHRStatusDto.getUserId());

			if (!userexists) {
				throw new RuntimeException("user with id " + createUpdateHRStatusDto.getUserId() + " not exists.");
			} else {
				hrStatus.setUser(userRepository.findById(createUpdateHRStatusDto.getUserId()).get());
			}
		}

		// set title, description, workdate
		if(!createUpdateHRStatusDto.getTitle().isEmpty())
			hrStatus.setTitle(createUpdateHRStatusDto.getTitle());
		if(!createUpdateHRStatusDto.getDescription().isEmpty())
			hrStatus.setDescription(createUpdateHRStatusDto.getDescription());
		hrStatus.setWorkDate(createUpdateHRStatusDto.getWorkDate());

		
		hrStatus = hrStatusRepository.save(hrStatus);
		LOGGER.info("Crated/Updated HRStatus record:" + hrStatus.getId());
		return hrStatus;
	}

	public void delete(final String id) {

		Optional<HRStatus> hrStatusEntity = hrStatusRepository.findById(id);

		if (hrStatusEntity.isEmpty())
			throw new InvalidInputException("Invalid hrStatus id: " + id);

		hrStatusRepository.deleteById(id);
	}

}
