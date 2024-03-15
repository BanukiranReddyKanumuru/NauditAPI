package com.ensar.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ensar.entity.HRSubtask;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.HRSubtaskRepository;
import com.ensar.repository.HRTaskRepository;
import com.ensar.request.dto.CreateUpdateHRSubtaskDto;


@Service
public class HRSubtaskService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HRSubtaskRepository hrSubtaskRepository;


	@Autowired
	private HRTaskRepository hrtaskRepository;


	public HRSubtask getHRSubtaskById(String id) {
		Optional<HRSubtask> hrSubtaskOptional = hrSubtaskRepository.findById(id);

		if (!hrSubtaskOptional.isPresent())
			throw new RuntimeException("HRSubtask with " + id + " not found.");
		return hrSubtaskOptional.get();
	}

	public List<HRSubtask> getAllHRSubtask() {
		List<HRSubtask> list = hrSubtaskRepository.findAll();

		return list;
	}

	@Transactional
	public HRSubtask createOrUpdateHRSubtask(Optional<String> hrSubtaskId,
			CreateUpdateHRSubtaskDto createUpdateHRSubtaskDto) {
		HRSubtask hrSubtask;

		// if id present, update record. Verify that id exist in database
		if (hrSubtaskId.isPresent()) {
			hrSubtask = hrSubtaskRepository.getById(hrSubtaskId.get());
			LOGGER.info("Updating HRSubtask record:" + hrSubtaskId.get());
			if (hrSubtask == null)
				throw new RuntimeException("HRSubtask with id " + hrSubtaskId.get() + " not found");
		} else {
			hrSubtask = new HRSubtask();
			LOGGER.info("Creating new HRSubtask record");
		}


		// set Task
		if (createUpdateHRSubtaskDto.getTaskId() != null && !createUpdateHRSubtaskDto.getTaskId().isEmpty()) {
			boolean taskexists = hrtaskRepository.existsById(createUpdateHRSubtaskDto.getTaskId());

			if (!taskexists) {
				throw new RuntimeException("task with id " + createUpdateHRSubtaskDto.getTaskId() + " not exists.");
			} else {
				hrSubtask.setTask(hrtaskRepository.findById(createUpdateHRSubtaskDto.getTaskId()).get());
			}
		}

	

		
		// set name
		
		if(!createUpdateHRSubtaskDto.getName().isEmpty())
			hrSubtask.setName(createUpdateHRSubtaskDto.getName());
		
		
		hrSubtask = hrSubtaskRepository.save(hrSubtask);
		LOGGER.info("Crated/Updated HRSubtask record:" + hrSubtask.getId());
		return hrSubtask;
	}

	public void delete(final String id) {

		Optional<HRSubtask> hrSubtaskEntity = hrSubtaskRepository.findById(id);

		if (hrSubtaskEntity.isEmpty())
			throw new InvalidInputException("Invalid hrSubtask id: " + id);

		hrSubtaskRepository.deleteById(id);
	}

}
