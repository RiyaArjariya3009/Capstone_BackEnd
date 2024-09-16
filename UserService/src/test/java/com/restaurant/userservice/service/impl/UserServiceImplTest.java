package com.restaurant.userservice.service.impl;

import com.restaurant.userservice.dto.in.UserLoginRequestDto;
import com.restaurant.userservice.dto.in.UserRegistrationRequestDto;
import com.restaurant.userservice.dto.out.UserLoginResponseDto;
import com.restaurant.userservice.dto.out.UserRegistrationResponseDto;
import com.restaurant.userservice.entities.User;
import com.restaurant.userservice.enums.RoleType;
import com.restaurant.userservice.exceptions.InvalidCredentialsException;
import com.restaurant.userservice.exceptions.UserExistsException;
import com.restaurant.userservice.exceptions.UserNotFoundException;
import com.restaurant.userservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link UserServiceImpl}.
 *
 * <p>This class contains unit tests for the methods in the UserServiceImpl
 * class, focusing on user registration and login functionalities.</p>
 */
class UserServiceImplTest {

    /**
     * Mocked instance of {@link UserRepository} for simulating database operations.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * Injected instance of {@link UserServiceImpl} where dependencies are mocked.
     */
    @InjectMocks
    private UserServiceImpl userService;

    /**
     * Sets up the test environment before each test case execution.
     *
     * <p>This method initializes the mock objects and injects them into the
     * userService object.</p>
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the successful registration of a new user.
     *
     * <p>This test case verifies that a new user can be successfully
     * registered when the email and contact number are unique.</p>
     */
    @Test
    void testRegisterUserSuccess() {
        UserRegistrationRequestDto requestDto = new UserRegistrationRequestDto();
        requestDto.setUsername("JohnDoe");
        requestDto.setEmail("johndoe@example.com");
        requestDto.setPassword("password123");
        requestDto.setRole(RoleType.CUSTOMER);
        requestDto.setContactNumber("1234567890");

        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.empty());
        when(userRepository.existsByContactNumber("1234567890")).thenReturn(false);

        User user = new User();
        user.setId(1);
        user.setUsername("JohnDoe");
        user.setEmail("johndoe@example.com");
        user.setRole(RoleType.CUSTOMER);
        user.setWalletBalance(1000.0);

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserRegistrationResponseDto responseDto = userService.registerUser(requestDto);

        assertNotNull(responseDto);
        assertEquals("JohnDoe", responseDto.getUsername());
        assertEquals("johndoe@example.com", responseDto.getEmail());
        assertEquals(RoleType.CUSTOMER.name(), responseDto.getRole());
        assertEquals(1000.0, responseDto.getWalletBalance());
        verify(userRepository, times(1)).save(any(User.class));
    }

    /**
     * Tests that the registration fails when the email is already in use.
     *
     * <p>This test case verifies that an exception is thrown if the email
     * provided during registration is already associated with an existing
     * user.</p>
     */
    @Test
    void testRegisterUserUserExists() {
        UserRegistrationRequestDto requestDto = new UserRegistrationRequestDto();
        requestDto.setUsername("JohnDoe");
        requestDto.setEmail("johndoe@example.com");
        requestDto.setPassword("password123");
        requestDto.setRole(RoleType.CUSTOMER);
        requestDto.setContactNumber("1234567890");

        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.of(new User()));

        assertThrows(UserExistsException.class, () -> userService.registerUser(requestDto));
        verify(userRepository, never()).save(any(User.class));
    }

    /**
     * Tests the successful login of a user.
     *
     * <p>This test case verifies that a user can successfully log in when
     * the provided email and password are correct.</p>
     */
    @Test
    void testLoginUserSuccess() {
        UserLoginRequestDto loginRequest = new UserLoginRequestDto();
        loginRequest.setEmail("johndoe@example.com");
        loginRequest.setPassword("password123");

        User user = new User();
        user.setId(1);
        user.setUsername("JohnDoe");
        user.setEmail("johndoe@example.com");
        user.setPassword(Base64.getEncoder().encodeToString("password123".getBytes(StandardCharsets.UTF_8)));
        user.setRole(RoleType.CUSTOMER);

        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.of(user));

        UserLoginResponseDto responseDto = userService.loginUser(loginRequest);

        assertNotNull(responseDto);
        assertEquals(1, responseDto.getUserId());
        assertEquals("JohnDoe", responseDto.getUsername());
        assertEquals(RoleType.CUSTOMER.name(), responseDto.getRole());
        assertEquals("johndoe@example.com", responseDto.getEmail());
        verify(userRepository, times(1)).findByEmail("johndoe@example.com");
    }

    /**
     * Tests that login fails when the user is not found.
     *
     * <p>This test case verifies that an exception is thrown if the user
     * is not found by the provided email during login.</p>
     */
    @Test
    void testLoginUserUserNotFound() {
        UserLoginRequestDto loginRequest = new UserLoginRequestDto();
        loginRequest.setEmail("johndoe@example.com");
        loginRequest.setPassword("password123");

        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.loginUser(loginRequest));
        verify(userRepository, times(1)).findByEmail("johndoe@example.com");
    }

    /**
     * Tests that login fails when the password is incorrect.
     *
     * <p>This test case verifies that an exception is thrown if the password
     * provided during login is incorrect.</p>
     */
    @Test
    void testLoginUserInvalidPassword() {
        UserLoginRequestDto loginRequest = new UserLoginRequestDto();
        loginRequest.setEmail("johndoe@example.com");
        loginRequest.setPassword("wrongpassword");

        User user = new User();
        user.setId(1);
        user.setUsername("JohnDoe");
        user.setEmail("johndoe@example.com");
        user.setPassword(Base64.getEncoder().encodeToString("password123".getBytes(StandardCharsets.UTF_8)));
        user.setRole(RoleType.CUSTOMER);

        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.of(user));

        assertThrows(InvalidCredentialsException.class, () -> userService.loginUser(loginRequest));
        verify(userRepository, times(1)).findByEmail("johndoe@example.com");
    }
}
