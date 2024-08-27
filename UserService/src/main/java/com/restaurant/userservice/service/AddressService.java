package com.restaurant.userservice.service;

import com.restaurant.userservice.dto.in.AddressRequestDto;
import com.restaurant.userservice.dto.out.AddressResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    AddressResponseDto addAddress(int userId, AddressRequestDto addressAddDTO);
    AddressResponseDto updateAddress(int userId, AddressRequestDto addressAddDTO);
    AddressResponseDto deleteAddress(int addressId);
    AddressResponseDto getAllAddressesByUserId(int userId);

}
