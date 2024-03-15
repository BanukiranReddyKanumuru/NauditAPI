package com.ensar.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ensar.entity.FileDB;
import com.ensar.entity.Software;
import com.ensar.entity.Systems;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.ManufactureRepository;
import com.ensar.repository.SoftwareRepository;
import com.ensar.repository.SystemsRepository;
import com.ensar.repository.UserRepository;
import com.ensar.request.dto.CreateUpdateSystemsDto;

@Service
public class SystemsService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private SystemsRepository systemsRepository;
	
	@Autowired
	private SoftwareRepository softwareRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ManufactureRepository manufactureRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	 @Autowired
	 private FileStorageService storageService;

	public Systems getSystemsById(String id) {
		Optional<Systems> systemOptional = systemsRepository.findById(id);

		if (!systemOptional.isPresent())
			throw new RuntimeException("Systems with " + id + " not found.");
		return systemOptional.get();
	}

	public List<Systems> getAllSystems() {
		List<Systems> list = systemsRepository.findAll();

		return list;
	}

	@Transactional
	public Systems createOrUpdateSystems(Optional<String> orgId, CreateUpdateSystemsDto createUpdateSystemsDto) {
		Systems system;
		if (orgId.isPresent()) {
			system = systemsRepository.getById(orgId.get());
			if (system == null)
				throw new RuntimeException("Systems with id " + orgId.get() + " not found");
		} else {
			system = new Systems();
			if (systemsRepository.existsByName(createUpdateSystemsDto.getName()))
				throw new RuntimeException(
						"Systems with name " + createUpdateSystemsDto.getName() + " already exists.");
		}
		
	
		if (createUpdateSystemsDto.getManufacturerId() != null && !createUpdateSystemsDto.getManufacturerId().isEmpty()) {
			boolean manufacturerexists = manufactureRepository.existsById(createUpdateSystemsDto.getManufacturerId());

			if (!manufacturerexists) {
				throw new RuntimeException(
						"manufacturer with id " + createUpdateSystemsDto.getManufacturerId() + " not exists.");
			} else {
				LOGGER.info(createUpdateSystemsDto.getManufacturerId());
				system.setManufacturer(manufactureRepository.findById(createUpdateSystemsDto.getManufacturerId()).get());
			}
		}
		if (createUpdateSystemsDto.getUserId() != null && !createUpdateSystemsDto.getUserId().isEmpty()) {
			boolean userexists = userRepository.existsById(createUpdateSystemsDto.getUserId());

			if (!userexists) {
				throw new RuntimeException(
						"user with id " + createUpdateSystemsDto.getUserId() + " not exists.");
			} else {
				system.setUser(userRepository.findById(createUpdateSystemsDto.getUserId()).get());
			}
		}
	
		List<String> list = Arrays.stream(createUpdateSystemsDto.getSoftwareIds())
                
                .collect(Collectors.toList());
				
		system.setSystemSoftware(softwareRepository.findAllByNameIn(list));
		system.setName(createUpdateSystemsDto.getName());
		system.setMacAddress(createUpdateSystemsDto.getMacAddress());
		system.setUser(userRepository.findById(createUpdateSystemsDto.getUserId()).get());
		system.setCpu(createUpdateSystemsDto.getCpu());
		system.setHardDisk(createUpdateSystemsDto.getHardDisk());
		system.setOs(createUpdateSystemsDto.getOs());
		system.setRam(createUpdateSystemsDto.getRam());
		system.setPurchasedDate(createUpdateSystemsDto.getPurchasedDate());
		system.setTags(createUpdateSystemsDto.getTags());
		system = systemsRepository.save(system);
		return system;
	}
	
	 public FileDB uploadImage(String id, MultipartFile file) throws IOException {
	 
		 Systems systems = getSystemsById(id);
		  
		  Optional<String> fileDBId = Optional.empty();
		  if (systems.getFileDB() != null)
		  {
			  fileDBId = Optional.of(systems.getFileDB().getId());
		  }
		    
		  FileDB fileDB = storageService.createOrUpdate(fileDBId, file);
		  systems.setFileDB(fileDB);
		  systemsRepository.save(systems);
		  
		  return storageService.createOrUpdate(fileDBId, file);
	 }

	@Transactional 
	public void csvToSystems(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<Systems> systemslist = new ArrayList<Systems>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	      for (CSVRecord csvRecord : csvRecords) {
	    	  Systems system = new Systems(
	            );

	    
	  		system.setName(csvRecord.get("Name"));
	  		system.setMacAddress(csvRecord.get("MacAddress"));
	  		system.setCpu(csvRecord.get("CPU"));
	  		system.setHardDisk(csvRecord.get("HardDisk"));
	  		system.setOs(csvRecord.get("OS"));
	  		system.setRam(csvRecord.get("RAM"));
	  		
	  		
	    	systemslist.add(system);
	      }
	      systemsRepository.saveAll(systemslist);


	      
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }
	
	public void delete(final String id) {

		Optional<Systems> systemEntity = systemsRepository.findById(id);

		if (systemEntity.isEmpty())
			throw new InvalidInputException("Invalid system id: " + id);

		systemsRepository.deleteById(id);
	}

}
