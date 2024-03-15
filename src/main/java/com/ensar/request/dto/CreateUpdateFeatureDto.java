package com.ensar.request.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(value = "CreateUpdateFeatureDto", description = "Parameters required to create/update Feature")
@Accessors(chain = true)

@Getter
public class CreateUpdateFeatureDto {
	
	@ApiModelProperty(notes = "Name", required = true)
    @NotBlank(message = "Name is required")
    @Size(max = 50)
	private String name;
	
	@ApiModelProperty(notes = "HostLocation", required = true)
    @NotBlank(message = "HostLocation is required")
    @Size(max = 50)
	private String hostLocation;
	
	@ApiModelProperty(notes = "Owner", required = true)
    @NotBlank(message = "Owner is required")
    @Size(max = 50)
	private String owner;
	
	@ApiModelProperty(notes = "ReviewCycle", required = true)
    @NotBlank(message = "ReviewCycle is required")
    @Size(max = 50)
	private String reviewCycle;
	
//	@ApiModelProperty(notes = "LastReviwed_Date", required = true)
//    private Date lastreviewed_date;
	
	@ApiModelProperty(notes = "Software")
    private String softwareid;

	
	}
