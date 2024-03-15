package com.ensar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.Crm;

@Repository
public interface CrmRepository extends JpaRepository<Crm, String> {

	
	Crm findByEmail(String email);

   
}
