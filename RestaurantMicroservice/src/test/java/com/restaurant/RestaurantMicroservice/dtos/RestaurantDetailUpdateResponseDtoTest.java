package com.restaurant.RestaurantMicroservice.dtos;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test class for {@link RestaurantDetailUpdateResponseDto}.
 * This class tests the getters, setters, equals, hashCode, and toString methods of the DTO.
 */
public class RestaurantDetailUpdateResponseDtoTest {

    /**
     * Test to verify the proper functioning of the getters and setters
     * of the {@link RestaurantDetailUpdateResponseDto}.
     */
    @Test
    public void testGettersAndSetters() {
        RestaurantDetailUpdateResponseDto dto = new RestaurantDetailUpdateResponseDto();
        byte[] imageData = {1, 2, 3, 4};

        dto.setId(1);
        dto.setRestaurantName("Test Restaurant");
        dto.setEmail("test@example.com");
        dto.setAddress("123 Test St");
        dto.setContactNo("9876543210");
        dto.setOpeningHours("9 AM - 9 PM");
        dto.setImageData(imageData);
        dto.setMessage("Update Successful");

        assertEquals(1, dto.getId());
        assertEquals("Test Restaurant", dto.getRestaurantName());
        assertEquals("test@example.com", dto.getEmail());
        assertEquals("123 Test St", dto.getAddress());
        assertEquals("9876543210", dto.getContactNo());
        assertEquals("9 AM - 9 PM", dto.getOpeningHours());
        assertArrayEquals(imageData, dto.getImageData());
        assertEquals("Update Successful", dto.getMessage());
    }

    /**
     * Test to verify the equals and hashCode methods of {@link RestaurantDetailUpdateResponseDto}.
     */
    @Test
    public void testEqualsAndHashCode() {
        RestaurantDetailUpdateResponseDto dto1 = new RestaurantDetailUpdateResponseDto();
        RestaurantDetailUpdateResponseDto dto2 = new RestaurantDetailUpdateResponseDto();
        RestaurantDetailUpdateResponseDto dto3 = new RestaurantDetailUpdateResponseDto();
        byte[] imageData = {1, 2, 3, 4};
        byte[] differentImageData = {5, 6, 7, 8};

        dto1.setId(1);
        dto1.setRestaurantName("Test Restaurant");
        dto1.setEmail("test@example.com");
        dto1.setAddress("123 Test St");
        dto1.setContactNo("9876543210");
        dto1.setOpeningHours("9 AM - 9 PM");
        dto1.setImageData(imageData);
        dto1.setMessage("Update Successful");

        dto2.setId(1);
        dto2.setRestaurantName("Test Restaurant");
        dto2.setEmail("test@example.com");
        dto2.setAddress("123 Test St");
        dto2.setContactNo("9876543210");
        dto2.setOpeningHours("9 AM - 9 PM");
        dto2.setImageData(imageData);
        dto2.setMessage("Update Successful");

        dto3.setId(2); // Different ID

        // Test equality
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // Test hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    /**
     * Test to verify the toString method of {@link RestaurantDetailUpdateResponseDto}.
     */
    @Test
    public void testToString() {
        RestaurantDetailUpdateResponseDto dto = new RestaurantDetailUpdateResponseDto();
        byte[] imageData = {1, 2, 3, 4};

        dto.setId(1);
        dto.setRestaurantName("Test Restaurant");
        dto.setEmail("test@example.com");
        dto.setAddress("123 Test St");
        dto.setContactNo("9876543210");
        dto.setOpeningHours("9 AM - 9 PM");
        dto.setImageData(imageData);
        dto.setMessage("Update Successful");

        String expectedString = "RestaurantDetailUpdateResponseDto{"
                +
                "id=" + dto.getId()
                +
                ", restaurantName='"
                + dto.getRestaurantName() + '\''
                +
                ", email='" + dto.getEmail()
                + '\''
                +
                ", address='"
                + dto.getAddress() + '\''
                +
                ", contactNo='"
                + dto.getContactNo() + '\''
                +
                ", openingHours='"
                + dto.getOpeningHours() + '\''
                +
                ", imageData="
                + Arrays.toString(dto.getImageData())
                +
                ", message='" + dto.getMessage()
                + '\''
                + '}';

        assertEquals(expectedString, dto.toString());
    }
}
