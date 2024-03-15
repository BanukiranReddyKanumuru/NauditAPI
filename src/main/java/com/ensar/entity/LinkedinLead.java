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

@Entity(name = "LinkedinLead")
@Data
@EqualsAndHashCode(callSuper = true)
public class LinkedinLead extends BaseEntity {

    @Column(name = "website_link")
    private String websiteLink;
 
    @Column(name = "linkedinLink")
    private String linkedinLink;

    @Column(name = "leadName")
    private String leadName;

    @Column(name = "responseType")
    private String responseType;

    @Column(name = "sent")
    private String sent;

    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
