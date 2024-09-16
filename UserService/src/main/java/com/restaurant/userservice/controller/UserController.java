package com.restaurant.userservice.controller;

import com.restaurant.userservice.dto.in.UserLoginRequestDto;
import com.restaurant.userservice.dto.in.UserRegistrationRequestDto;
import com.restaurant.userservice.dto.in.UserUpdateRequestDto;
import com.restaurant.userservice.dto.out.CommonResponseDto;
import com.restaurant.userservice.dto.out.UserLoginResponseDto;
import com.restaurant.userservice.dto.out.UserRegistrationResponseDto;
import com.restaurant.userservice.dto.out.UserResponseDto;
import com.restaurant.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * UserController handles HTTP requests related to user management.
 * It provides endpoints for user registration, login, retrieval, and update.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public final class UserController {

    /**
     * Logger for the UserController class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * Service for handling user-related operations.
     */
    @Autowired
    private UserService userService;


    /**
     * Registers a new user.
     *
     * @param userDto The DTO containing user registration information.
     * @return A ResponseEntity containing the registered user details and HTTP status CREATED.
     */
    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseDto> registerUser(
            @Valid @RequestBody UserRegistrationRequestDto userDto) {

        LOGGER.info("Attempting to register user with email: {}", userDto.getEmail());
        UserRegistrationResponseDto newUser = userService.registerUser(userDto);
        LOGGER.info("User registered successfully with email: {}", userDto.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    /**
     * Logs in a user.
     *
     * @param userLoginDto The DTO containing user login information.
     * @return A ResponseEntity containing the login response and HTTP status OK.
     */
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> loginUser(
            @Valid @RequestBody UserLoginRequestDto userLoginDto) {

        LOGGER.info("Attempting to login user with email: {}", userLoginDto.getEmail());
        UserLoginResponseDto userResponse = userService.loginUser(userLoginDto);
        LOGGER.info("User logged in successfully with email: {}", userLoginDto.getEmail());
        return ResponseEntity.ok(userResponse);
    }

    /**
     * Retrieves user details by user ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return A ResponseEntity containing user details and HTTP status OK.
     */
    @GetMapping("getUserBy/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") int id) {
        UserResponseDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Updates user details by user ID.
     *
     * @param id The ID of the user to be updated.
     * @param updateRequest The DTO containing updated user information.
     * @return A ResponseEntity containing a common response DTO and HTTP status OK.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<CommonResponseDto> updateUser(
            @PathVariable int id,
            @Valid @RequestBody UserUpdateRequestDto updateRequest) {

        CommonResponseDto responseDTO = userService.updateUser(id, updateRequest);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
