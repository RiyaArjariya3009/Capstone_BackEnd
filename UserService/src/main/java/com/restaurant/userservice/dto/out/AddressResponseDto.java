package com.restaurant.userservice.dto.out;
import com.restaurant.userservice.dto.in.AddressRequestDto;

import java.util.List;

public class AddressResponseDto {

    private int userId;
    private List<AddressRequestDto> addresses;

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<AddressRequestDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressRequestDto> addresses) {
        this.addresses = addresses;
    }

    public AddressResponseDto(int userId, List<AddressRequestDto> addresses) {
        this.userId = userId;
        this.addresses = addresses;
    }
}