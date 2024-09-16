package com.restaurant.userservice.service;


import com.restaurant.userservice.dto.in.UserLoginRequestDto;
import com.restaurant.userservice.dto.out.UserLoginResponseDto;
import com.restaurant.userservice.dto.in.UserRegistrationRequestDto;
import com.restaurant.userservice.dto.out.UserRegistrationResponseDto;
import com.restaurant.userservice.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    UserRegistrationResponseDto registerUser(UserRegistrationRequestDto userDto);
   /* Optional<User> findByEmail(String email);*/
    UserLoginResponseDto loginUser(UserLoginRequestDto userLoginDto);

      User getAllUsers(String email);
}

