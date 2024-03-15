package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@Entity(name = "nist_systems_software")
@Data
@EqualsAndHashCode(callSuper = true)

public class SystemsSoftware extends BaseEntity {

	/*@Column(name = "nist_systems_id")
    String systemsId;
   
	
    @Column(name = "nist_software_id")
    String softwareId;*/
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nist_systems_id")
	@JsonIgnoreProperties("systemsSoftwares")
    private Systems systems;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nist_software_id")
    @JsonIgnoreProperties("systemsSoftwares")
    private Software software;
}
