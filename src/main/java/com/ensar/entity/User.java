package com.ensar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "user")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    public enum Role  { ROLE_SUPER_ADMIN, ROLE_ADMIN, ROLE_USER }

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "join_date")
    private Date joinDate;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "disabled")
    private boolean disabled;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "last_login_date_time")
    private Timestamp lastLoginDateTime;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles roleId;

    @OneToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;

}
