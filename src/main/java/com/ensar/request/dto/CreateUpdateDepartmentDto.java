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

@ApiModel(value = "CreateUpdateDepartmentDto", description = "Parameters required to create/update Department")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateDepartmentDto {


    @ApiModelProperty(notes = "Department name")
    @NotBlank(message = "Department name is required")
    @Size(max = 200)
    private String name;


}
