package com.restaurant.userservice.dto.in;

import com.restaurant.userservice.enums.RoleType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginRequestDto {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
   /* @NotNull(message = "Not null role type")
    private RoleType role;*/

    public UserLoginRequestDto()
    {
        super();
    }

    public UserLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
       // this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
