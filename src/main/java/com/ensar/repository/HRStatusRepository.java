package com.ensar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.HRStatus;
import com.ensar.entity.Software;
import com.ensar.entity.Systems;

@Repository
public interface HRStatusRepository extends JpaRepository<HRStatus, String> {

	

   
}
