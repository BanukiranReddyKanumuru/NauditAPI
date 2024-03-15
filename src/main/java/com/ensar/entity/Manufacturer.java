package com.ensar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "manufacturer")
@Data
@EqualsAndHashCode(callSuper = true)
public class Manufacturer extends BaseEntity {

	@Column(name = "name")
	private String name;

}
