package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.Controls;

@Repository
public interface ControlsRepository extends JpaRepository<Controls, String> {

	void deleteById(Integer id);

	boolean existsByName(String name);

}
