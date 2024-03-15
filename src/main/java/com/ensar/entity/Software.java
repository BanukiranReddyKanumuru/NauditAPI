package com.ensar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "nist_software")
@Data
@EqualsAndHashCode(callSuper = true)
public class Software extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "version ")
	private String version;

	@Column(name = "owner ")
	private String owner;

	@Column(name = "vendor")
	private String vendor;

	@Column(name = "install_date")
	private Date installdate;

	@Column(name = "support")
	private String support;

	@Column(name = "license")
	private String license;
	/*
	 * @ManyToMany(mappedBy = "systemSoftware") Set<Systems> systems = new
	 * HashSet<>();
	 */
	/*
	 * @ManyToMany(cascade = CascadeType.ALL)
	 * 
	 * @JsonBackReference
	 * 
	 * @JoinTable(name = "nist_systems_software", joinColumns = @JoinColumn(name =
	 * "nist_software_id", referencedColumnName = "id"), inverseJoinColumns
	 * = @JoinColumn(name = "nist_systems_id", referencedColumnName = "id"))
	 * Set<Systems> softwareSystems;
	 */

}
