package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity(name = "nist_systems")
@Data
@EqualsAndHashCode(callSuper = true)
public class Systems extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "mac_address")
    private String macAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(name = "nist_systems_software", joinColumns = @JoinColumn(name = "nist_systems_id"), 
    	inverseJoinColumns = @JoinColumn(name = "nist_software_id"))

    Set<Software> systemSoftware;

    @Column(name = "os")
    private String os;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "hard_disk")
    private String hardDisk;

    @Column(name = "ram")
    private String ram;
    
    @Column(name = "price")
    private int price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacture_id", referencedColumnName = "id")
    private Manufacturer manufacturer;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "files_id", referencedColumnName = "id")
    private FileDB fileDB;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "software_id", referencedColumnName = "id")
    // private Software software;

    @Column(name = "purchased_date")
    private Date purchasedDate;

    @Column(name = "Tags")
    private String tags;

    /*
     * @OneToMany(mappedBy = "systems", cascade = CascadeType.ALL)
     * 
     * @JsonIgnoreProperties("systems")
     * private Set<SystemsSoftware> systemsSoftwares;
     */
    /*
     * public Systems(String name, SystemsSoftware... systemsSoftwares) {
     * this.name = name;
     * for(SystemsSoftware systemsSoftware : systemsSoftwares)
     * systemsSoftware.setSystems(this);
     * this.systemsSoftwares =
     * Stream.of(systemsSoftwares).collect(Collectors.toSet());
     * }
     */

}
