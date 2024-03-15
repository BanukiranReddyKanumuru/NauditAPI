package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "user_login_tracker")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginTracker extends BaseEntity {

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_ip")
    private String userIp;

    @Column(name = "succeeded")
    private boolean succeeded;

}
