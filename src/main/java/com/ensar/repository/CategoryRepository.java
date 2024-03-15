package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

	void deleteById(Integer id);

	boolean existsByName(String name);

}
