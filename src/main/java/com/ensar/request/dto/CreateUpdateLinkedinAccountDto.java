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

@ApiModel(value = "CreateUpdateLinkedinAccountDto", description = "Parameters required to create/update LinkedinAccount")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateLinkedinAccountDto {

    @ApiModelProperty(notes = "LinkedinAccount email", required = true)
    @NotBlank(message = "LinkedinAccount email is required")
    @Size(max = 50)
    private String email;

    @ApiModelProperty(notes = "LinkedinAccount First Name", required = true)
    @NotBlank(message = "LinkedinAccount First Name is required")
    @Size(max = 50)
    private String firstName;

    @ApiModelProperty(notes = "LinkedinAccount last Name")
    @Size(max = 50)
    private String lastName;

    @ApiModelProperty(notes = "LinkedinAccount Password", required = true)
    @NotBlank(message = "LinkedinAccount Password is required")
    @Size(max = 50)
    private String password;

    @ApiModelProperty(notes = "LinkedinAccount Type")
    @Size(max = 50)
    private String type;

    @ApiModelProperty(notes = "User")
    private String userId;
}
