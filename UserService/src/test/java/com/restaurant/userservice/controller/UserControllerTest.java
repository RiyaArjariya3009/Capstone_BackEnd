package com.restaurant.userservice.controller;

import com.restaurant.userservice.dto.in.UserLoginRequestDto;
import com.restaurant.userservice.dto.in.UserRegistrationRequestDto;
import com.restaurant.userservice.dto.out.UserLoginResponseDto;
import com.restaurant.userservice.dto.out.UserRegistrationResponseDto;
import com.restaurant.userservice.dto.out.UserResponseDto;
import com.restaurant.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link UserController}.
 * It contains unit tests for all endpoints in the UserController class.
 */
public class UserControllerTest {

    /**
     * Mock instance of UserService to be used in tests.
     */
    @Mock
    private UserService userService;

    /**
     * Instance of UserController to be tested.
     */
    @InjectMocks
    private UserController userController;

    /**
     * MockMvc instance for performing HTTP requests in tests.
     */
    private MockMvc mockMvc;

    /**
     * Sets up the testing environment before each test.
     * Initializes mocks and sets up MockMvc.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    /**
     * Tests the registration of a new user.
     * Ensures that the UserService is called with the correct parameters and
     * the response returned by the UserController is as expected.
     */
    @Test
    public void testRegisterUser() {
        UserRegistrationRequestDto requestDto = new UserRegistrationRequestDto();
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("password");
        // Set other fields as necessary

        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto();
        responseDto.setEmail("test@example.com");
        // Set other fields as necessary

        when(userService.registerUser(requestDto)).thenReturn(responseDto);

        ResponseEntity<UserRegistrationResponseDto> response = userController.registerUser(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "HTTP status should be CREATED (201)");
        assertEquals(responseDto, response.getBody(), "Response body should match the expected UserRegistrationResponseDto");
    }

    /**
     * Tests the login of a user.
     * Ensures that the UserService is called with the correct parameters and
     * the response returned by the UserController is as expected.
     */
    @Test
    public void testLoginUser() {
        UserLoginRequestDto requestDto = new UserLoginRequestDto();
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("password");

        UserLoginResponseDto responseDto = new UserLoginResponseDto();
        responseDto.setEmail("test@example.com");
        // Set other fields as necessary

        when(userService.loginUser(requestDto)).thenReturn(responseDto);

        ResponseEntity<UserLoginResponseDto> response = userController.loginUser(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "HTTP status should be OK (200)");
        assertEquals(responseDto, response.getBody(), "Response body should match the expected UserLoginResponseDto");
    }

    /**
     * Tests retrieving user details by user ID.
     * Ensures that the UserService is called with the correct user ID and
     * the response returned by the UserController is as expected.
     */
    @Test
    public void testGetUserById() {
        int userId = 1;
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(userId);
        responseDto.setEmail("test@example.com");
        // Set other fields as necessary

        when(userService.getUserById(userId)).thenReturn(responseDto);

        ResponseEntity<UserResponseDto> response = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "HTTP status should be OK (200)");
        assertEquals(responseDto, response.getBody(), "Response body should match the expected UserResponseDto");
    }
}
