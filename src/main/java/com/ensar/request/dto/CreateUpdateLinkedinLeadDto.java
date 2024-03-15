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

@ApiModel(value = "CreateUpdateLinkedinLeadDto", description = "Parameters required to create/update LinkedinLead")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateLinkedinLeadDto {


    @ApiModelProperty(notes = "LinkedinLead website link")
    @NotBlank(message = "LinkedinLead website link is required")
    @Size(max = 200)
    private String websiteLink;

    @ApiModelProperty(notes = "LinkedinAccount leadName", required = true)
    @NotBlank(message = "LinkedinAccount leadName is required")
    @Size(max = 50)
    private String leadName;

    @ApiModelProperty(notes = "LinkedinAccount Linkedin Link", required = true)
    @NotBlank(message = "LinkedinAccount Linkedin Link is required")
    @Size(max = 50)
    private String linkedinLink;

    @ApiModelProperty(notes = "LinkedinAccount response type")
    @Size(max = 50)
    private String responseType;

    @ApiModelProperty(notes = "LinkedinAccount sent")
    @Size(max = 50)
    private String sent;

    @ApiModelProperty(notes = "User")
    private String userId;
}