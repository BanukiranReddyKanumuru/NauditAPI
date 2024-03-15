package com.ensar.request.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.experimental.Accessors;

@ApiModel(value = "CreateUpdateSubcategoryDto", description = "Parameters required to create/update Subcategory")
@Accessors(chain = true)

@Getter
public class CreateUpdateSubcategoryDto {

	@ApiModelProperty(notes = "Name", required = true)
	@NotBlank(message = "Name is required")
	@Size(max = 50)
	private String name;

	@ApiModelProperty(notes = "Category")
	@NotBlank(message = "categoryid is required")
	@Size(max = 50)
	private String categoryid;

}
