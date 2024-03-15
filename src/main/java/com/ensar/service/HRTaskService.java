package com.ensar.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.HRTask;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.HRTaskRepository;
import com.ensar.request.dto.CreateUpdateHRTaskDto;


@Service
public class HRTaskService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HRTaskRepository hrTaskRepository;



	public HRTask getHRTaskById(String id) {
		Optional<HRTask> hrTaskOptional = hrTaskRepository.findById(id);

		if (!hrTaskOptional.isPresent())
			throw new RuntimeException("HRTask with " + id + " not found.");
		return hrTaskOptional.get();
	}

	public List<HRTask> getAllHRTask() {
		List<HRTask> list = hrTaskRepository.findAll();

		return list;
	}

	@Transactional
	public HRTask createOrUpdateHRTask(Optional<String> hrTaskId,
			CreateUpdateHRTaskDto createUpdateHRTaskDto) {
		HRTask hrTask;

		// if id present, update record. Verify that id exist in database
		if (hrTaskId.isPresent()) {
			hrTask = hrTaskRepository.getById(hrTaskId.get());
			LOGGER.info("Updating HRTask record:" + hrTaskId.get());
			if (hrTask == null)
				throw new RuntimeException("HRTask with id " + hrTaskId.get() + " not found");
		} else {
			hrTask = new HRTask();
			LOGGER.info("Creating new HRTask record");
		}

		
		// set name
		
		if(!createUpdateHRTaskDto.getName().isEmpty())
			hrTask.setName(createUpdateHRTaskDto.getName());

		
		hrTask = hrTaskRepository.save(hrTask);
		LOGGER.info("Crated/Updated HRTask record:" + hrTask.getId());
		return hrTask;
	}

	public void delete(final String id) {

		Optional<HRTask> hrTaskEntity = hrTaskRepository.findById(id);

		if (hrTaskEntity.isEmpty())
			throw new InvalidInputException("Invalid hrTask id: " + id);

		hrTaskRepository.deleteById(id);
	}

}
