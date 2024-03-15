package com.ensar.request.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.ensar.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(value = "CreateUpdateManufactureDto", description = "Parameters required to create/update manufacture")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateManufactureDto {
	
	
	
	@ApiModelProperty(notes = "Manufacture Name", required = true)
    @NotBlank(message = "Manufacture Name is required")
    private String name;
	
	
	@ApiModelProperty(notes = "Manufacture Location", required = true)
    @NotBlank(message = "Manufacture Location is required")
    private String location;
	

}
