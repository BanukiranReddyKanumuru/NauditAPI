package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ensar.entity.Organization;

import com.ensar.entity.User;

import java.util.List;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, String> {

	User findByEmail(String email);

	Optional<User> findById(String id);

	boolean existsByEmail(String email);

	List<User> findByOrganization(Organization organization);

	List<User> findByOrganizationId(String organizationId);

}