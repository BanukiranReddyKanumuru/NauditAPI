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

@Entity(name = "hr_timesheet_attendance")
@Data
@EqualsAndHashCode(callSuper = true)
public class HRTimesheetAttendance extends BaseEntity {
	 
    @Column(name = "remarks")
    private String remarks;

    @Column(name = "hours")
    private int hours;

    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "projects_id", referencedColumnName = "id")
    private HRProject project;

    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private HRTask task;
    
    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "subtask_id", referencedColumnName = "id")
    private HRSubtask subtask;
    
    @Column(name = "work_date")
    private Date workDate;
    
    
    
    

    
}
