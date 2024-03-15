package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ensar.entity.User;

@ApiModel(value = "CreateUpdateUserDto", description = "Parameters required to create/update user")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateUserDto {

    @ApiModelProperty(notes = "User Email", required = true)
    @NotBlank(message = "User Email is required")
    @Email
    private String email;

    @ApiModelProperty(notes = "User First Name", required = true)
    @NotBlank(message = "User First Name is required")
    @Size(max = 50)
    private String firstName;

    @ApiModelProperty(notes = "User Last Name", required = true)
    @NotBlank(message = "User Last Name is required")
    @Size(max = 50)
    private String lastName;

    @ApiModelProperty(notes = "User Mobile")
    @Size(max = 12)
    private String mobile;

    @ApiModelProperty(notes = "User Employee Id")
    @Size(max = 50)
    private String employeeId;

    @ApiModelProperty(notes = "User Join Date")
    private Date joinDate;

    @ApiModelProperty(notes = "User Role")
    public User.Role roleName;

    @ApiModelProperty(notes = "User Organization")
    private String organizationId;

    @ApiModelProperty(notes = "User Role")
    private String roleId;
}
