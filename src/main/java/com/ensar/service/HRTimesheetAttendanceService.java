package com.ensar.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ensar.entity.HRTimesheetAttendance;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.HRTimesheetAttendanceRepository;
import com.ensar.repository.HRProjectRepository;
import com.ensar.repository.HRTaskRepository;
import com.ensar.repository.HRSubtaskRepository;
import com.ensar.repository.UserRepository;
import com.ensar.request.dto.CreateUpdateHRTimesheetAttendanceDto;


@Service
public class HRTimesheetAttendanceService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HRTimesheetAttendanceRepository hrTimesheetAttendanceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HRProjectRepository hrprojectRepository;

	@Autowired
	private HRTaskRepository hrtaskRepository;

	@Autowired
	private HRSubtaskRepository hrsubtaskRepository;

	public HRTimesheetAttendance getHRTimesheetAttendanceById(String id) {
		Optional<HRTimesheetAttendance> hrTimesheetAttendanceOptional = hrTimesheetAttendanceRepository.findById(id);

		if (!hrTimesheetAttendanceOptional.isPresent())
			throw new RuntimeException("HRTimesheetAttendance with " + id + " not found.");
		return hrTimesheetAttendanceOptional.get();
	}

	public List<HRTimesheetAttendance> getAllHRTimesheetAttendance() {
		List<HRTimesheetAttendance> list = hrTimesheetAttendanceRepository.findAll();

		return list;
	}

	@Transactional
	public HRTimesheetAttendance createOrUpdateHRTimesheetAttendance(Optional<String> hrTimesheetAttendanceId,
			CreateUpdateHRTimesheetAttendanceDto createUpdateHRTimesheetAttendanceDto) {
		HRTimesheetAttendance hrTimesheetAttendance;

		// if id present, update record. Verify that id exist in database
		if (hrTimesheetAttendanceId.isPresent()) {
			hrTimesheetAttendance = hrTimesheetAttendanceRepository.getById(hrTimesheetAttendanceId.get());
			LOGGER.info("Updating HRTimesheetAttendance record:" + hrTimesheetAttendanceId.get());
			if (hrTimesheetAttendance == null)
				throw new RuntimeException("HRTimesheetAttendance with id " + hrTimesheetAttendanceId.get() + " not found");
		} else {
			hrTimesheetAttendance = new HRTimesheetAttendance();
			LOGGER.info("Creating new HRTimesheetAttendance record");
		}

		// set user
		if (createUpdateHRTimesheetAttendanceDto.getUserId() != null && !createUpdateHRTimesheetAttendanceDto.getUserId().isEmpty()) {
			boolean userexists = userRepository.existsById(createUpdateHRTimesheetAttendanceDto.getUserId());

			if (!userexists) {
				throw new RuntimeException("user with id " + createUpdateHRTimesheetAttendanceDto.getUserId() + " not exists.");
			} else {
				hrTimesheetAttendance.setUser(userRepository.findById(createUpdateHRTimesheetAttendanceDto.getUserId()).get());
			}
		}
		
		// set Project
		if (createUpdateHRTimesheetAttendanceDto.getProjectsId() != null && !createUpdateHRTimesheetAttendanceDto.getProjectsId().isEmpty()) {
			boolean projectexists = hrprojectRepository.existsById(createUpdateHRTimesheetAttendanceDto.getProjectsId());

			if (!projectexists) {
				throw new RuntimeException("project with id " + createUpdateHRTimesheetAttendanceDto.getProjectsId() + " not exists.");
			} else {
				hrTimesheetAttendance.setProject(hrprojectRepository.findById(createUpdateHRTimesheetAttendanceDto.getProjectsId()).get());
			}
		}
		
		// set Task
		if (createUpdateHRTimesheetAttendanceDto.getTaskId() != null && !createUpdateHRTimesheetAttendanceDto.getTaskId().isEmpty()) {
			boolean taskexists = hrtaskRepository.existsById(createUpdateHRTimesheetAttendanceDto.getTaskId());

			if (!taskexists) {
				throw new RuntimeException("task with id " + createUpdateHRTimesheetAttendanceDto.getTaskId() + " not exists.");
			} else {
				hrTimesheetAttendance.setTask(hrtaskRepository.findById(createUpdateHRTimesheetAttendanceDto.getTaskId()).get());
			}
		}

		// set Subtask
		if (createUpdateHRTimesheetAttendanceDto.getSubtaskId() != null && !createUpdateHRTimesheetAttendanceDto.getSubtaskId().isEmpty()) {
			boolean subtaskexists = hrsubtaskRepository.existsById(createUpdateHRTimesheetAttendanceDto.getSubtaskId());

			if (!subtaskexists) {
				throw new RuntimeException("task with id " + createUpdateHRTimesheetAttendanceDto.getSubtaskId() + " not exists.");
			} else {
				hrTimesheetAttendance.setSubtask(hrsubtaskRepository.findById(createUpdateHRTimesheetAttendanceDto.getSubtaskId()).get());
			}
		}

		
		// set title, description, hours, workdate
		
		if(!createUpdateHRTimesheetAttendanceDto.getRemarks().isEmpty())
			hrTimesheetAttendance.setRemarks(createUpdateHRTimesheetAttendanceDto.getRemarks());
		hrTimesheetAttendance.setHours(createUpdateHRTimesheetAttendanceDto.getHours());
		hrTimesheetAttendance.setWorkDate(createUpdateHRTimesheetAttendanceDto.getWorkDate());

		
		hrTimesheetAttendance = hrTimesheetAttendanceRepository.save(hrTimesheetAttendance);
		LOGGER.info("Crated/Updated HRTimesheetAttendance record:" + hrTimesheetAttendance.getId());
		return hrTimesheetAttendance;
	}

	public void delete(final String id) {

		Optional<HRTimesheetAttendance> hrTimesheetAttendanceEntity = hrTimesheetAttendanceRepository.findById(id);

		if (hrTimesheetAttendanceEntity.isEmpty())
			throw new InvalidInputException("Invalid hrTimesheetAttendance id: " + id);

		hrTimesheetAttendanceRepository.deleteById(id);
	}

}
