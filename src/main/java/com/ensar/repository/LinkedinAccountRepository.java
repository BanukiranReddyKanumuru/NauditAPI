package com.ensar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.LinkedinAccount;

@Repository
public interface LinkedinAccountRepository extends JpaRepository<LinkedinAccount, String> {

	
	LinkedinAccount findByEmail(String email);

   
}
