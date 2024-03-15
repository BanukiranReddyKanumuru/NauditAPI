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

@ApiModel(value = "CreateUpdateHRTaskDto", description = "Parameters required to create/update HRTask")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateHRTaskDto {


    @ApiModelProperty(notes = "HRTask name")
    @NotBlank(message = "HRTask name is required")
    @Size(max = 200)
    private String name;


}
