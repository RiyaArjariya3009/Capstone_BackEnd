package com.restaurant.userservice.dto.out;

import com.restaurant.userservice.enums.RoleType;

public class UserLoginResponseDto {

   // private int id;
    private String username;

    private String email;
    private String role;

    public UserLoginResponseDto()
    {
        super();
    }
    // Constructor
    public UserLoginResponseDto(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role=role;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
