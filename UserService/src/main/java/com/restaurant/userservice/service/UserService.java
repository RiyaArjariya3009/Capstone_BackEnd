package com.restaurant.userservice.service;

import com.restaurant.userservice.dto.in.UserLoginRequestDto;
import com.restaurant.userservice.dto.in.UserUpdateRequestDto;
import com.restaurant.userservice.dto.out.CommonResponseDto;
import com.restaurant.userservice.dto.out.UserLoginResponseDto;
import com.restaurant.userservice.dto.in.UserRegistrationRequestDto;
import com.restaurant.userservice.dto.out.UserRegistrationResponseDto;
import com.restaurant.userservice.dto.out.UserResponseDto;
import org.springframework.stereotype.Service;

/**
 * Service interface for user-related operations.
 * Provides methods for user registration, login, update, and retrieval.
 */
@Service
public interface UserService {

    /**
     * Registers a new user with the given details.
     *
     * @param userDto the registration details of the user.
     * @return a response containing registration details and status.
     */
    UserRegistrationResponseDto registerUser(UserRegistrationRequestDto userDto);

    /**
     * Authenticates a user with the provided login credentials.
     *
     * @param userLoginDto the login credentials of the user.
     * @return a response containing login details and status.
     */
    UserLoginResponseDto loginUser(UserLoginRequestDto userLoginDto);

    /**
     * Updates the details of an existing user.
     *
     * @param id the ID of the user to be updated.
     * @param updateRequest the new details to update.
     * @return a response indicating the result of the update operation.
     */
    CommonResponseDto updateUser(int id, UserUpdateRequestDto updateRequest);

    /**
     * Retrieves the details of a user by their ID.
     *
     * @param userId the ID of the user to retrieve.
     * @return a response containing the user's details.
     */
    UserResponseDto getUserById(int userId);
}
