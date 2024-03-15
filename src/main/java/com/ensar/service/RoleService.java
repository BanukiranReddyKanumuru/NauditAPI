package com.ensar.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.Roles;
import com.ensar.exception.InvalidInputException;
import com.ensar.repository.RoleRepository;
import com.ensar.request.dto.CreateUpdateRoleDto;


@Service
public class RoleService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleRepository roleRepository;



	public Roles getRoleById(String id) {
		Optional<Roles> roleOptional = roleRepository.findById(id);

		if (!roleOptional.isPresent())
			throw new RuntimeException("Roles with " + id + " not found.");
		return roleOptional.get();
	}

	public List<Roles> getAllRole() {
		List<Roles> list = roleRepository.findAll();

		return list;
	}

	@Transactional
	public Roles createOrUpdateRole(Optional<String> roleId,
			CreateUpdateRoleDto createUpdateRoleDto) {
		Roles role;

		// if id present, update record. Verify that id exist in database
		if (roleId.isPresent()) {
			role = roleRepository.getById(roleId.get());
			LOGGER.info("Updating Role record:" + roleId.get());
			if (role == null)
				throw new RuntimeException("Role with id " + roleId.get() + " not found");
		} else {
			role = new Roles();
			LOGGER.info("Creating new Role record");
		}

		
		// set name
		
		if(!createUpdateRoleDto.getName().isEmpty())
			role.setName(createUpdateRoleDto.getName());

		
		role = roleRepository.save(role);
		LOGGER.info("Crated/Updated Role record:" + role.getId());
		return role;
	}

	public void delete(final String id) {

		Optional<Roles> roleEntity = roleRepository.findById(id);

		if (roleEntity.isEmpty())
			throw new InvalidInputException("Invalid role id: " + id);

		roleRepository.deleteById(id);
	}

}
