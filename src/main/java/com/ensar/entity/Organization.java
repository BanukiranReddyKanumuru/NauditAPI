package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "organization")
@Data
@EqualsAndHashCode(callSuper = true)
public class Organization extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "domain")
    private String domain;

    @Column(name = "disabled")
    private boolean disabled;

    @Column(name = "logo_img_src")
    private String logoImgSrc;

}
