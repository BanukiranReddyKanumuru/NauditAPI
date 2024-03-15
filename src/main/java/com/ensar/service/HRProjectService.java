package com.ensar.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.HRProject;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.HRProjectRepository;
import com.ensar.request.dto.CreateUpdateHRProjectDto;


@Service
public class HRProjectService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HRProjectRepository hrProjectRepository;



	public HRProject getHRProjectById(String id) {
		Optional<HRProject> hrProjectOptional = hrProjectRepository.findById(id);

		if (!hrProjectOptional.isPresent())
			throw new RuntimeException("HRProject with " + id + " not found.");
		return hrProjectOptional.get();
	}

	public List<HRProject> getAllHRProject() {
		List<HRProject> list = hrProjectRepository.findAll();

		return list;
	}

	@Transactional
	public HRProject createOrUpdateHRProject(Optional<String> hrProjectId,
			CreateUpdateHRProjectDto createUpdateHRProjectDto) {
		HRProject hrProject;

		// if id present, update record. Verify that id exist in database
		if (hrProjectId.isPresent()) {
			hrProject = hrProjectRepository.getById(hrProjectId.get());
			LOGGER.info("Updating HRProject record:" + hrProjectId.get());
			if (hrProject == null)
				throw new RuntimeException("HRProject with id " + hrProjectId.get() + " not found");
		} else {
			hrProject = new HRProject();
			LOGGER.info("Creating new HRProject record");
		}

		
		// set name
		
		if(!createUpdateHRProjectDto.getName().isEmpty())
			hrProject.setName(createUpdateHRProjectDto.getName());

		
		hrProject = hrProjectRepository.save(hrProject);
		LOGGER.info("Crated/Updated HRProject record:" + hrProject.getId());
		return hrProject;
	}

	public void delete(final String id) {

		Optional<HRProject> hrProjectEntity = hrProjectRepository.findById(id);

		if (hrProjectEntity.isEmpty())
			throw new InvalidInputException("Invalid hrProject id: " + id);

		hrProjectRepository.deleteById(id);
	}

}
