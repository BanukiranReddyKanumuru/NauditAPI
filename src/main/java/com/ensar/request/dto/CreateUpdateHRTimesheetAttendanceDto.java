package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(value = "CreateUpdateHRTimesheetAttendanceDto", description = "Parameters required to create/update HRTimesheetAttendance")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateHRTimesheetAttendanceDto {

//    @ApiModelProperty(notes = "HRTimesheetAttendance hours")
//    @NotBlank(message = "HRTimesheetAttendance Hours is required")
//    @Size(max = 50)
//    private int hours;

    @ApiModelProperty(notes = "HRTimesheetAttendance remarks")
    @NotBlank(message = "HRTimesheetAttendance remarks is required")
    @Size(max = 2000)
    private String remarks;

    @ApiModelProperty(notes = "User")
    private String userId;
    
    @ApiModelProperty(notes = "HRTimesheetAttendance hours")
    private int hours;
    
    @ApiModelProperty(notes = "HRProject")
    private String projectsId;
    
    @ApiModelProperty(notes = "Tasks")
    private String taskId;
   
    @ApiModelProperty(notes = "Subtask")
    private String subtaskId;

    @ApiModelProperty(notes = "HRTimesheetAttendance workdate")
    private Date workDate;

    

}
