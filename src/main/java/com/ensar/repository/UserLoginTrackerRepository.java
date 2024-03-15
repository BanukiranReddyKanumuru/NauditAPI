package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.UserLoginTracker;

@Repository
public interface UserLoginTrackerRepository extends JpaRepository<UserLoginTracker, String> {

}
