package com.restaurant.userservice.dtos.out;
import com.restaurant.userservice.dto.out.UserLoginResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link com.restaurant.userservice.dto.out.UserLoginResponseDto}.
 * Contains test cases to ensure that the DTO behaves as expected.
 */
public class UserLoginResponseDtoTest {

    /**
     * Tests the default constructor and parameterized constructor
     * of {@link com.restaurant.userservice.dto.out.UserLoginResponseDto}.
     */
    @Test
    public void testConstructors() {
        // Arrange
        int expectedUserId = 1;
        String expectedUsername = "testUser";
        String expectedEmail = "test@example.com";
        String expectedRole = "USER";
        String expectedMessage = "Login successful";

        // Act
        UserLoginResponseDto dto = new UserLoginResponseDto(expectedUserId, expectedUsername, expectedEmail, expectedRole,
                expectedMessage);

        // Assert
        assertEquals(expectedUserId, dto.getUserId(), "User ID should match");
        assertEquals(expectedUsername, dto.getUsername(), "Username should match");
        assertEquals(expectedEmail, dto.getEmail(), "Email should match");
        assertEquals(expectedRole, dto.getRole(), "Role should match");
        assertEquals(expectedMessage, dto.getMessage(), "Message should match");
    }

    /**
     * Tests the setters and getters of {@link UserLoginResponseDto}.
     */
    @Test
    public void testSettersAndGetters() {
        // Arrange
        UserLoginResponseDto dto = new UserLoginResponseDto();
        int expectedUserId = 2;
        String expectedUsername = "anotherUser";
        String expectedEmail = "another@example.com";
        String expectedRole = "ADMIN";
        String expectedMessage = "Login successful with admin rights";

        // Act
        dto.setUserId(expectedUserId);
        dto.setUsername(expectedUsername);
        dto.setEmail(expectedEmail);
        dto.setRole(expectedRole);
        dto.setMessage(expectedMessage);

        // Assert
        assertEquals(expectedUserId, dto.getUserId(), "User ID should match");
        assertEquals(expectedUsername, dto.getUsername(), "Username should match");
        assertEquals(expectedEmail, dto.getEmail(), "Email should match");
        assertEquals(expectedRole, dto.getRole(), "Role should match");
        assertEquals(expectedMessage, dto.getMessage(), "Message should match");
    }
}

