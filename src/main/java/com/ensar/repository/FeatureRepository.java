package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, String> {
	void deleteById(Integer id);

	boolean existsByName(String name);

}
