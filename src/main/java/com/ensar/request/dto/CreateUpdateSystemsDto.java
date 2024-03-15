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

@ApiModel(value = "CreateUpdateSystemsDto", description = "Parameters required to create/update organization")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateSystemsDto {

    @ApiModelProperty(notes = "Systems Name", required = true)
    @NotBlank(message = "Systems Name is required")
    @Size(max = 50)
    private String name;

    @ApiModelProperty(notes = "Systems macaddress")
   
    @Size(max = 12)
    private String macAddress;

    @ApiModelProperty(notes = "User")

    private String userId;

    @ApiModelProperty(notes = "Systems OS")
    // @NotBlank(message = "Systems OS is required")
    @Size(max = 12)
    private String os;

    @ApiModelProperty(notes = "Systems CPU")
    // @NotBlank(message = "Systems CPU is required")
    @Size(max = 12)
    private String cpu;

    @ApiModelProperty(notes = "Systems Harddisk")
    // @NotBlank(message = "Systems Harddisk is required")
    @Size(max = 12)
    private String hardDisk;

    @ApiModelProperty(notes = "Systems RAM")
    // @NotBlank(message = "Systems RAM is required")
    @Size(max = 6)
    private String ram;

    @ApiModelProperty(notes = "manufacturer")
   
    private String manufacturerId;

  

    @ApiModelProperty(notes = "Systems purchaseddate")

    private Date purchasedDate;

    @ApiModelProperty(notes = "Systems Software")
    // @NotBlank(message = "Systems Tags is required")
    private String[] softwareIds;
    
    @ApiModelProperty(notes = "Systems Tags")
    // @NotBlank(message = "Systems Tags is required")
    private String tags;
    
    @ApiModelProperty(notes = "Systems Image")
   
    private String image;

}
