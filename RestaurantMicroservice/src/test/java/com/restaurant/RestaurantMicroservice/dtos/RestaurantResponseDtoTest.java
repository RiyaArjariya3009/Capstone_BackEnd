package com.restaurant.RestaurantMicroservice.dtos;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link RestaurantResponseDto}. It tests the functionality
 * of the getters, setters, equals, hashCode, and toString methods of the DTO class.
 */
public class RestaurantResponseDtoTest {

    /**
     * Tests the getters and setters for {@link RestaurantResponseDto}.
     * Ensures that the values set via the setters are correctly retrieved using the getters.
     */
    @Test
    public void testGettersAndSetters() {
        RestaurantResponseDto dto = new RestaurantResponseDto();
        byte[] image = {1, 2, 3, 4};

        dto.setId(1);
        dto.setOwnerId(101);
        dto.setRestaurantName("Test Restaurant");
        dto.setEmail("test@example.com");
        dto.setAddress("123 Test St");
        dto.setContactNo("9876543210");
        dto.setOpen(true);
        dto.setOpeningHours("9 AM - 9 PM");
        dto.setImageUrl(image);

        assertEquals(1, dto.getId());
        assertEquals(101, dto.getOwnerId());
        assertEquals("Test Restaurant", dto.getRestaurantName());
        assertEquals("test@example.com", dto.getEmail());
        assertEquals("123 Test St", dto.getAddress());
        assertEquals("9876543210", dto.getContactNo());
        assertTrue(dto.getOpen());
        assertEquals("9 AM - 9 PM", dto.getOpeningHours());
        assertArrayEquals(image, dto.getImageUrl());
    }

    /**
     * Tests the equality and hashCode implementation of {@link RestaurantResponseDto}.
     * Verifies that two objects with the same values are considered equal
     * and have the same hashCode.
     */
    @Test
    public void testEqualsAndHashCode() {
        RestaurantResponseDto dto1 = new RestaurantResponseDto();
        RestaurantResponseDto dto2 = new RestaurantResponseDto();
        RestaurantResponseDto dto3 = new RestaurantResponseDto();
        byte[] image = {1, 2, 3, 4};
        byte[] differentImage = {5, 6, 7, 8};

        dto1.setId(1);
        dto1.setOwnerId(101);
        dto1.setRestaurantName("Test Restaurant");
        dto1.setEmail("test@example.com");
        dto1.setAddress("123 Test St");
        dto1.setContactNo("9876543210");
        dto1.setOpen(true);
        dto1.setOpeningHours("9 AM - 9 PM");
        dto1.setImageUrl(image);

        dto2.setId(1);
        dto2.setOwnerId(101);
        dto2.setRestaurantName("Test Restaurant");
        dto2.setEmail("test@example.com");
        dto2.setAddress("123 Test St");
        dto2.setContactNo("9876543210");
        dto2.setOpen(true);
        dto2.setOpeningHours("9 AM - 9 PM");
        dto2.setImageUrl(image);

        dto3.setId(2); // Different ID

        // Test equality
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // Test hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    /**
     * Tests the {@link RestaurantResponseDto#toString()} method.
     * Ensures that the string representation of the object is as expected.
     */
    @Test
    public void testToString() {
        RestaurantResponseDto dto = new RestaurantResponseDto();
        byte[] image = {1, 2, 3, 4};

        dto.setId(1);
        dto.setOwnerId(101);
        dto.setRestaurantName("Test Restaurant");
        dto.setEmail("test@example.com");
        dto.setAddress("123 Test St");
        dto.setContactNo("9876543210");
        dto.setOpen(true);
        dto.setOpeningHours("9 AM - 9 PM");
        dto.setImageUrl(image);

        String expectedString = "RestaurantResponseDto{"
                +
                "id="
                + dto.getId()
                +
                ", restaurantName='"
                + dto.getRestaurantName()
                + '\''
                +
                ", email='" + dto.getEmail()
                + '\''
                +
                ", address='" + dto.getAddress()
                + '\''
                +
                ", contactNo='" + dto.getContactNo()
                + '\''
                +
                ", open="
                + dto.getOpen()
                +
                ", openingHours='" + dto.getOpeningHours()
                + '\''
                +
                ", imageUrl=" + Arrays.toString(dto.getImageUrl())
                +
                ", ownerId=" + dto.getOwnerId()
                +
                '}';

        assertEquals(expectedString, dto.toString());
    }
}
