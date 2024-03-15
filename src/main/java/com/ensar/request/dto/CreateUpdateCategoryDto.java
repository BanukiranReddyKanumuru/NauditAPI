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

@ApiModel(value = "CreateUpdateCategoryDto", description = "Parameters required to create/update Category")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateCategoryDto {
	
	@ApiModelProperty(notes = "Name", required = true)
    @NotBlank(message = "Name is required")
    @Size(max = 50)
	private String name;
	
	@ApiModelProperty(notes = "Feature")
    private String featureid;
}