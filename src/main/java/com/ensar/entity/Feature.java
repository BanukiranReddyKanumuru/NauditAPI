package com.ensar.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "feature")
@Data
@EqualsAndHashCode(callSuper = true)
public class Feature extends BaseEntity {

	@Column(name = "name")
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "software_id", referencedColumnName = "id")
	private Software software;

	@Column(name = "host_location")
	private String hostlocation;

	@Column(name = "owner")
	private String owner;

	@Column(name = "review_cycle")
	private String reviewcycle;

//	@Column(name = "lastReviewed_Date")
//	  private Date lastReviewed_date;

}
