package com.restaurant.RestaurantMicroservice.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link CommonResponseDto}.
 */
public class CommonResponseDtoTest {

    /**
     * Tests the getters and setters of {@link CommonResponseDto}.
     */
    @Test
    public void testGettersAndSetters() {
        CommonResponseDto dto = new CommonResponseDto();
        dto.setMessage("Test message");

        assertEquals("Test message", dto.getMessage(), "Message should be 'Test message'");
    }

    /**
     * Tests the {@code equals} and {@code hashCode} methods of {@link CommonResponseDto}.
     */
    @Test
    public void testEqualsAndHashCode() {
        CommonResponseDto dto1 = new CommonResponseDto();
        dto1.setMessage("Test message");

        CommonResponseDto dto2 = new CommonResponseDto();
        dto2.setMessage("Test message");

        CommonResponseDto dto3 = new CommonResponseDto();
        dto3.setMessage("Different message");

        // Test equality
        assertEquals(dto1, dto2, "dto1 should be equal to dto2");
        assertNotEquals(dto1, dto3, "dto1 should not be equal to dto3");

        // Test hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode(), "Hash codes of dto1 and dto2 should be equal");
        assertNotEquals(dto1.hashCode(), dto3.hashCode(), "Hash codes of dto1 and dto3 should not be equal");
    }

    /**
     * Tests the {@code toString} method of {@link CommonResponseDto}.
     */
    @Test
    public void testToString() {
        CommonResponseDto dto = new CommonResponseDto();
        dto.setMessage("Test message");

        String expectedString = "CommonResponseDto{message='Test message'}";
        assertEquals(expectedString, dto.toString(), "toString() method should return the expected string");
    }
}
