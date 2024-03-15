package com.ensar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "employee")
@Data
@EqualsAndHashCode(callSuper = true)
public class Employees extends BaseEntity {

	@Column(name = "email")
	private String email;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "employeeId")
	private String employeeId;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "join_date")
	private Date joinDate;

}