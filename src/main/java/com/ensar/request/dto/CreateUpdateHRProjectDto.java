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

@ApiModel(value = "CreateUpdateHRProjectDto", description = "Parameters required to create/update HRProject")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateHRProjectDto {


    @ApiModelProperty(notes = "HRProject name")
    @NotBlank(message = "HRProject name is required")
    @Size(max = 50)
    private String name;


}
