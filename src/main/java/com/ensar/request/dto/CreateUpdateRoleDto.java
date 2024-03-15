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

@ApiModel(value = "CreateUpdateRoleDto", description = "Parameters required to create/update Role")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateRoleDto {


    @ApiModelProperty(notes = "Role name")
    @NotBlank(message = "Role name is required")
    @Size(max = 200)
    private String name;


}
