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

@ApiModel(value = "CreateUpdateHRSubtaskDto", description = "Parameters required to create/update HRSubtask")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateHRSubtaskDto {



    @ApiModelProperty(notes = "HRSubtask name")
    @NotBlank(message = "HRSubtask name is required")
    @Size(max = 2000)
    private String name;


    @ApiModelProperty(notes = "Tasks")
    private String taskId;
   

    

}
