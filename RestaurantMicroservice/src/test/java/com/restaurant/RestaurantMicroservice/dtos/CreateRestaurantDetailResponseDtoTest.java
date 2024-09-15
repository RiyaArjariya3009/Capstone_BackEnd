package com.restaurant.RestaurantMicroservice.dtos;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link CreateRestaurantDetailResponseDto} class.
 */
public class CreateRestaurantDetailResponseDtoTest {

    /**
     * Tests the getters and setters of the {@link CreateRestaurantDetailResponseDto} class.
     */
    @Test
    public void testGettersAndSetters() {
        CreateRestaurantDetailResponseDto dto = new CreateRestaurantDetailResponseDto();
        byte[] imageData = {1, 2, 3};

        dto.setRestaurantName("Test Restaurant");
        dto.setAddress("123 Test Street");
        dto.setMessage("Restaurant created successfully");
        dto.setOwnerName("John Doe");
        dto.setOpen(true);
        dto.setContactNo("9861234567");
        dto.setDescription("A great place to eat");
        dto.setImageData(imageData);

        assertEquals("Test Restaurant", dto.getRestaurantName());
        assertEquals("123 Test Street", dto.getAddress());
        assertEquals("Restaurant created successfully", dto.getMessage());
        assertEquals("John Doe", dto.getOwnerName());
        assertTrue(dto.getOpen());
        assertEquals("9861234567", dto.getContactNo());
        assertEquals("A great place to eat", dto.getDescription());
        assertArrayEquals(imageData, dto.getImageData());
    }

    /**
     * Tests the equality and hashCode methods of the {@link CreateRestaurantDetailResponseDto} class.
     */
    @Test
    public void testEqualsAndHashCode() {
        CreateRestaurantDetailResponseDto dto1 = new CreateRestaurantDetailResponseDto();
        CreateRestaurantDetailResponseDto dto2 = new CreateRestaurantDetailResponseDto();
        CreateRestaurantDetailResponseDto dto3 = new CreateRestaurantDetailResponseDto();
        byte[] imageData = {1, 2, 3};

        dto1.setRestaurantName("Test Restaurant");
        dto1.setAddress("123 Test Street");
        dto1.setMessage("Restaurant created successfully");
        dto1.setOwnerName("John Doe");
        dto1.setOpen(true);
        dto1.setContactNo("9861234567");
        dto1.setDescription("A great place to eat");
        dto1.setImageData(imageData);

        dto2.setRestaurantName("Test Restaurant");
        dto2.setAddress("123 Test Street");
        dto2.setMessage("Restaurant created successfully");
        dto2.setOwnerName("John Doe");
        dto2.setOpen(true);
        dto2.setContactNo("9861234567");
        dto2.setDescription("A great place to eat");
        dto2.setImageData(imageData);

        dto3.setRestaurantName("Another Restaurant"); // Different name

        // Test equality
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // Test hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    /**
     * Tests the {@link CreateRestaurantDetailResponseDto#toString()} method.
     */
    @Test
    public void testToString() {
        CreateRestaurantDetailResponseDto dto = new CreateRestaurantDetailResponseDto();
        byte[] imageData = {1, 2, 3};

        dto.setRestaurantName("Test Restaurant");
        dto.setAddress("123 Test Street");
        dto.setMessage("Restaurant created successfully");
        dto.setOwnerName("John Doe");
        dto.setOpen(true);
        dto.setContactNo("9861234567");
        dto.setDescription("A great place to eat");
        dto.setImageData(imageData);

        String expectedString = "CreateRestaurantDetailResponseDto{"
                +
                "restaurantName='Test Restaurant', address='123 Test Street', "
                +
                "message='Restaurant created successfully', ownerName='John Doe', "
                +
                "open=true, contactNo='9861234567', description='A great place to eat', "
                +
                "imageData=" + Arrays.toString(imageData) + '}';

        assertEquals(expectedString, dto.toString());
    }
}
