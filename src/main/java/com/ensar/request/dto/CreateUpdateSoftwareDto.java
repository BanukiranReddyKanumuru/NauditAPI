package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "CreateUpdateSoftwareDto", description = "Parameters required to create/update organization")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateSoftwareDto {

    @ApiModelProperty(notes = "Software Name", required = true)
    @NotBlank(message = "Software Name is required")
    @Size(max = 50)
    private String name;
    
    @ApiModelProperty(notes = "Software Version", required = true)
    @NotBlank(message = "Software Version is required")
    @Size(max = 50)
    private String version;

    @ApiModelProperty(notes = "Software Owner", required = true)
    @NotBlank(message = "Software Owner is required")
    @Size(max = 50)
    private String owner;

    @ApiModelProperty(notes = "Software vendor", required = true)
    @NotBlank(message = "Software vendor is required")
    @Size(max = 50)
    private String vendor;

    @ApiModelProperty(notes = "Software InstallDate", required = true)
    private Date install_date;

    @ApiModelProperty(notes = "Software Support", required = true)
    @NotBlank(message = "Software Support is required")
    @Size(max = 50)
    private String support;

    
    @ApiModelProperty(notes = "Software License", required = true)
    @NotBlank(message = "Software License is required")
    @Size(max = 50)
    private String license;

    
   

}
