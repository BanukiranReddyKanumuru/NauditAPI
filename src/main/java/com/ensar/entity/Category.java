package com.ensar.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "category")
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {

	@Column(name = "name")
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "feature_id", referencedColumnName = "id")
	private Feature feature;

}