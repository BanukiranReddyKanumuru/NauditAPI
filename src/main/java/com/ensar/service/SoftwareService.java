package com.ensar.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.Software;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.SoftwareRepository;
import com.ensar.request.dto.CreateUpdateSoftwareDto;

@Service
public class SoftwareService {
	private SoftwareRepository softwareRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public SoftwareService(SoftwareRepository softwareRepository) {
		this.softwareRepository = softwareRepository;

	}

	public Software getSoftwareById(String id) {
		Optional<Software> softwareOptional = softwareRepository.findById(id);

		if (!softwareOptional.isPresent())
			throw new RuntimeException("Software with " + id + " not found.");
		return softwareOptional.get();
	}

	public List<Software> getAllSoftwares() {
		List<Software> list = softwareRepository.findAll();

		return list;
	}

	public Software createOrUpdateSoftware(Optional<String> orgId, CreateUpdateSoftwareDto createUpdateSoftwareDto) {
		Software software;
		if (orgId.isPresent()) {
			software = softwareRepository.getById(orgId.get());
			if (software == null)
				throw new RuntimeException("Software with id " + orgId.get() + " not found");
		} else {
			software = new Software();
			if (softwareRepository.existsByName(createUpdateSoftwareDto.getName()))
				throw new RuntimeException(
						"Software with name " + createUpdateSoftwareDto.getName() + " already exists.");
		}

		software.setName(createUpdateSoftwareDto.getName());
		software.setVersion(createUpdateSoftwareDto.getVersion());
		software.setSupport(createUpdateSoftwareDto.getSupport());
		software.setLicense(createUpdateSoftwareDto.getLicense());
		software.setVendor(createUpdateSoftwareDto.getVendor());
		software.setVersion(createUpdateSoftwareDto.getVersion());
		software.setOwner(createUpdateSoftwareDto.getOwner());
		software.setInstalldate(createUpdateSoftwareDto.getInstall_date());
		software = softwareRepository.save(software);
		return software;
	}

	public void delete(final String id) {

		Optional<Software> SoftwareEntity = softwareRepository.findById(id);

		if (SoftwareEntity.isEmpty())
			throw new InvalidInputException("Invalid Software id: " + id);

		softwareRepository.deleteById(id);
	}

}
