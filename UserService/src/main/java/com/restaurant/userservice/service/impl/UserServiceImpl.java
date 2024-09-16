package com.restaurant.userservice.service.impl;

import com.restaurant.userservice.constants.Constants;
import com.restaurant.userservice.dto.in.UserLoginRequestDto;
import com.restaurant.userservice.dto.in.UserUpdateRequestDto;
import com.restaurant.userservice.dto.out.CommonResponseDto;
import com.restaurant.userservice.dto.out.UserLoginResponseDto;
import com.restaurant.userservice.dto.in.UserRegistrationRequestDto;
import com.restaurant.userservice.dto.out.UserRegistrationResponseDto;
import com.restaurant.userservice.dto.out.UserResponseDto;
import com.restaurant.userservice.entities.User;
import com.restaurant.userservice.enums.RoleType;
import com.restaurant.userservice.exceptions.InvalidCredentialsException;
import com.restaurant.userservice.exceptions.UserExistsException;
import com.restaurant.userservice.exceptions.UserNotFoundException;
import com.restaurant.userservice.repositories.UserRepository;
import com.restaurant.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

/**
 * Implementation of {@link UserService} for managing user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * Repository for managing {@link User} entities.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user.
     *
     * @param requestDTO the DTO containing the user registration details
     * @return the {@link UserRegistrationResponseDto} with the details of the registered user
     * @throws UserExistsException if a user with the specified email or contact number already exists
     */
    @Override
    public UserRegistrationResponseDto registerUser(UserRegistrationRequestDto requestDTO) {
        Optional<User> isUserExists = userRepository.findByEmail(requestDTO.getEmail());
        if (isUserExists.isPresent()) {
            throw new UserExistsException(Constants.EMAIL_ALREADY_IN_USE);
        }
        if (userRepository.existsByContactNumber(requestDTO.getContactNumber())) {
            throw new UserExistsException(Constants.NUMBER_ALREADY_IN_USE);
        }

        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(Base64.getEncoder().encodeToString(
                requestDTO.getPassword().getBytes(StandardCharsets.UTF_8)
        )); // Explicitly specify UTF-8 encoding for password
        user.setRole(requestDTO.getRole());
        user.setContactNumber(requestDTO.getContactNumber());

        if (user.getRole() == RoleType.CUSTOMER) {
            user.setWalletBalance(Constants.WALLET_BALANCE);
        } else {
            user.setWalletBalance(Constants.WALLET_BALANCE_NOT_FOUND);
        }

        userRepository.save(user);

        UserRegistrationResponseDto responseDTO = new UserRegistrationResponseDto();
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setPassword(user.getPassword());
        responseDTO.setRole(user.getRole().name());
        responseDTO.setWalletBalance(user.getWalletBalance());
        responseDTO.setUserId(user.getId());
        responseDTO.setMessage(Constants.REGISTRATION_SUCCESSFUL);
        return responseDTO;
    }

    /**
     * Logs in a user.
     *
     * @param loginRequest the DTO containing the login request details
     * @return the {@link UserLoginResponseDto} with the details of the logged-in user
     * @throws UserNotFoundException if the user with the specified email is not found
     * @throws InvalidCredentialsException if the provided password is incorrect
     */
    @Override
    public UserLoginResponseDto loginUser(UserLoginRequestDto loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND + loginRequest.getEmail()));

        String decodedPassword = new String(
                Base64.getDecoder().decode(user.getPassword()),
                StandardCharsets.UTF_8
        ); // Explicitly specify UTF-8 encoding for decoding password

        if (!loginRequest.getPassword().equals(decodedPassword)) {
            throw new InvalidCredentialsException(Constants.INVALID_CREDENTIALS);
        }

        UserLoginResponseDto responseDTO = new UserLoginResponseDto();
        responseDTO.setUserId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setRole(user.getRole().name());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setMessage(Constants.LOGIN_SUCCESSFUL);

        return responseDTO;
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the {@link UserResponseDto} with the details of the user
     * @throws UserNotFoundException if the user with the specified ID is not found
     */
    @Override
    public UserResponseDto getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND));

        UserResponseDto getUserResponse = new UserResponseDto();
        getUserResponse.setId(user.getId());
        getUserResponse.setUsername(user.getUsername());
        getUserResponse.setEmail(user.getEmail());
        getUserResponse.setRole(user.getRole().name());
        getUserResponse.setContactNumber(user.getContactNumber());
        getUserResponse.setWalletBalance(user.getWalletBalance());

        return getUserResponse;
    }

    /**
     * Updates a user's details.
     *
     * @param id the ID of the user to be updated
     * @param updateRequest the DTO containing the updated user details
     * @return a {@link CommonResponseDto} indicating the outcome of the update operation
     * @throws UserNotFoundException if the user with the specified ID is not found
     */
    @Override
    public CommonResponseDto updateUser(int id, UserUpdateRequestDto updateRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND));

        user.setUsername(updateRequest.getUsername());
        user.setContactNumber(updateRequest.getContactNumber());
        user.setEmail(updateRequest.getEmail());

        userRepository.save(user);

        return new CommonResponseDto(Constants.USER_UPDATED_SUCCESSFULLY);
    }
}
