package com.restaurant.userservice.dtos.out;
import com.restaurant.userservice.dto.out.CommonResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link com.restaurant.userservice.dto.out.CommonResponseDto}.
 * It contains unit tests for the getter and setter methods, and constructor of CommonResponseDto.
 */
public class CommonResponseDtoTest {

    /**
     * Tests the constructor of {@link com.restaurant.userservice.dto.out.CommonResponseDto}.
     * Ensures that the message is correctly set during construction.
     */
    @Test
    public void testConstructor() {
        String expectedMessage = "Success";
        CommonResponseDto dto = new CommonResponseDto(expectedMessage);

        assertNotNull(dto, "CommonResponseDto should not be null");
        assertEquals(expectedMessage, dto.getMessage(), "The message should be set by the constructor");
    }

    /**
     * Tests the getter method of {@link CommonResponseDto}.
     * Ensures that the message can be retrieved correctly.
     */
    @Test
    public void testGetMessage() {
        String expectedMessage = "Operation completed successfully";
        CommonResponseDto dto = new CommonResponseDto(expectedMessage);

        assertEquals(expectedMessage, dto.getMessage(), "The message should match the expected value");
    }

    /**
     * Tests the setter method of {@link CommonResponseDto}.
     * Ensures that the message can be set correctly.
     */
    @Test
    public void testSetMessage() {
        CommonResponseDto dto = new CommonResponseDto("Initial Message");
        String newMessage = "Updated Message";

        dto.setMessage(newMessage);

        assertEquals(newMessage, dto.getMessage(), "The message should be updated correctly");
    }
}

