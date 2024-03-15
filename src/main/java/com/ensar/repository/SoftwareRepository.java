package com.ensar.repository;


import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.Organization;
import com.ensar.entity.Software;

@Repository
public interface SoftwareRepository extends JpaRepository<Software, String> {
	
	Software findByName(String name);

	boolean existsByName(String name);
	
	Set<Software> findAllByNameIn(List<String> name);
}

