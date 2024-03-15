package com.ensar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.LinkedinAccount;
import com.ensar.entity.LinkedinLead;

@Repository
public interface LinkedinLeadRepository extends JpaRepository<LinkedinLead, String> {

	
	LinkedinAccount findByLeadName(String leadName);
   
}
