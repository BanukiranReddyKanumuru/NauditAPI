package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.Manufacturer;

@Repository
public interface ManufactureRepository extends JpaRepository<Manufacturer, String> {

	boolean existsByName(String name);

}