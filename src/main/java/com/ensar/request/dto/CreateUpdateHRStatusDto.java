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

@ApiModel(value = "CreateUpdateHRStatusDto", description = "Parameters required to create/update HRStatus")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateHRStatusDto {

    @ApiModelProperty(notes = "HRStatus Title", required = true)
    @NotBlank(message = "HRStatus Title is required")
    @Size(max = 50)
    private String title;

    @ApiModelProperty(notes = "HRStatus description")
    @NotBlank(message = "HRStatus description is required")
    @Size(max = 2000)
    private String description;

    @ApiModelProperty(notes = "User")
   
    private String userId;

   

    @ApiModelProperty(notes = "HRStatus workdate")

    private Date workDate;

    

}
