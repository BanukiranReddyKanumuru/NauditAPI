package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.Subcategory;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, String> {
	boolean existsByName(String name);

	void deleteById(Integer id);
}
