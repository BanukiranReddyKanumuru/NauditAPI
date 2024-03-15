package com.ensar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.HRTask;

@Repository
public interface HRTaskRepository extends JpaRepository<HRTask, String> {

	

   
}
