package com.ensar.request.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(value = "CreateUpdateControlsDto", description = "Parameters required to create/update controls")
@Accessors(chain = true)
@Setter
@Getter

public class CreateUpdateControlsDto {

	@ApiModelProperty(notes = "Controls Name", required = true)
	@NotBlank(message = "Controls Name is required")
	private String name;

	@ApiModelProperty(notes = "SubCategory id", required = true)
	@NotBlank(message = "SubCategory id is required")
	private String subcategory;

}
