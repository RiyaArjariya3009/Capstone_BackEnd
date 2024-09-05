package com.restaurant.userservice.dtos.out;



import com.restaurant.userservice.dto.out.UserRegistrationResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link com.restaurant.userservice.dto.out.UserRegistrationResponseDto} class.
 * These tests validate the behavior of the DTO, ensuring that the getters and setters
 * work as expected and that the object can be correctly instantiated.
 */
public class UserRegistrationResponseDtoTest {

    /**
     * Tests the default constructor of the {@link com.restaurant.userservice.dto.out.UserRegistrationResponseDto} class.
     * This test verifies that the default constructor initializes the fields to their default values.
     */
    @Test
    public void testDefaultConstructor() {
        UserRegistrationResponseDto dto = new UserRegistrationResponseDto();
        assertEquals(0, dto.getUserId(), "Default userId should be 0");
        assertEquals(null, dto.getUsername(), "Default username should be null");
        assertEquals(null, dto.getPassword(), "Default password should be null");
        assertEquals(null, dto.getEmail(), "Default email should be null");
        assertEquals(null, dto.getRole(), "Default role should be null");
        assertEquals(null, dto.getWalletBalance(), "Default walletBalance should be null");
        assertEquals(null, dto.getMessage(), "Default message should be null");
    }

    /**
     * Tests the parameterized constructor of the {@link UserRegistrationResponseDto} class.
     * This test verifies that the parameterized constructor correctly sets the fields of the DTO.
     */
    @Test
    public void testParameterizedConstructor() {
        UserRegistrationResponseDto dto = new UserRegistrationResponseDto(
                "JohnDoe",
                "password123",
                "john.doe@example.com",
                "USER",
                100.50,
                "Registration successful"
        );

        assertEquals("JohnDoe", dto.getUsername(), "Username should be set correctly");
        assertEquals("password123", dto.getPassword(), "Password should be set correctly");
        assertEquals("john.doe@example.com", dto.getEmail(), "Email should be set correctly");
        assertEquals("USER", dto.getRole(), "Role should be set correctly");
        assertEquals(100.50, dto.getWalletBalance(), "Wallet balance should be set correctly");
        assertEquals("Registration successful", dto.getMessage(), "Message should be set correctly");
    }

    /**
     * Tests the setter and getter methods of the {@link UserRegistrationResponseDto} class.
     * This test verifies that the setters correctly update the values of the fields and that
     * the getters return the updated values.
     */
    @Test
    public void testSettersAndGetters() {
        UserRegistrationResponseDto dto = new UserRegistrationResponseDto();

        dto.setUserId(1);
        dto.setUsername("JaneDoe");
        dto.setPassword("newpassword456");
        dto.setEmail("jane.doe@example.com");
        dto.setRole("ADMIN");
        dto.setWalletBalance(200.75);
        dto.setMessage("Updated successfully");

        assertEquals(1, dto.getUserId(), "UserId should be set correctly");
        assertEquals("JaneDoe", dto.getUsername(), "Username should be set correctly");
        assertEquals("newpassword456", dto.getPassword(), "Password should be set correctly");
        assertEquals("jane.doe@example.com", dto.getEmail(), "Email should be set correctly");
        assertEquals("ADMIN", dto.getRole(), "Role should be set correctly");
        assertEquals(200.75, dto.getWalletBalance(), "Wallet balance should be set correctly");
        assertEquals("Updated successfully", dto.getMessage(), "Message should be set correctly");
    }
}
