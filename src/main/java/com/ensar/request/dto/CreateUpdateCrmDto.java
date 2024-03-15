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

@ApiModel(value = "CreateUpdateCrmDto", description = "Parameters required to create/update Crm")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateCrmDto {
	
    @ApiModelProperty(notes = "Crm email", required = true)
    @NotBlank(message = "Crm email is required")
    @Size(max = 50)
    private String email;

    @ApiModelProperty(notes = "Crm First Name", required = true)
    @NotBlank(message = "Crm First Name is required")
    @Size(max = 50)
    private String firstName;

    @ApiModelProperty(notes = "Crm last Name")
    @Size(max = 50)
    private String lastName;

    @ApiModelProperty(notes = "Crm Password", required = true)
    @NotBlank(message = "Crm Password is required")
    @Size(max = 50)
    private String password;

    @ApiModelProperty(notes = "Crm Type")
    @Size(max = 50)
    private String type;
    
    @ApiModelProperty(notes = "User Join Date")
    private Date joinDate;

}
