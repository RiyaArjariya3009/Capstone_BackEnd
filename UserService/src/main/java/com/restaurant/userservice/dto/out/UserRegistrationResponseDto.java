package com.restaurant.userservice.dto.out;

import com.restaurant.userservice.enums.RoleType;

import java.math.BigDecimal;

public class UserRegistrationResponseDto {

    private String username;

    private String password;

    private String email;

    private BigDecimal walletBalance;


    private String role;

    public UserRegistrationResponseDto()
    {
        super();
    }

    public UserRegistrationResponseDto(String username, String password, String email,String role,BigDecimal walletBalance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.walletBalance=walletBalance;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public BigDecimal getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(BigDecimal walletBalance) {
        this.walletBalance = walletBalance;
    }

}
