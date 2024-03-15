package com.ensar.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "controls")
@Data
@EqualsAndHashCode(callSuper = true)
public class Controls extends BaseEntity {

	@Column(name = "name")
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subcategory_id", referencedColumnName = "id")
	private Subcategory subcategoryid;
}
