package com.restaurant.RestaurantMicroservice.dtos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Unit tests for {@link MenuItemResponseDto}.
 * <p>
 * This class tests the getters and setters, equals and hashCode methods, toString method,
 * and availability of the {@link MenuItemResponseDto} class.
 * </p>
 */
public class MenuItemResponseDtoTest {

    /**
     * Tests the getters and setters of {@link MenuItemResponseDto}.
     */
    @Test
    public void testGettersAndSetters() {
        MenuItemResponseDto dto = new MenuItemResponseDto();

        dto.setId(1);
        dto.setRestaurantName("Test Restaurant");
        dto.setCategoryName("Test Category");
        dto.setFoodName("Test Food");
        dto.setDescription("Test Description");
        dto.setIsAvailable(true);
        dto.setPrice(new BigDecimal("9.99"));
        dto.setImageData(new byte[]{1, 2, 3});

        assertEquals(1, dto.getId());
        assertEquals("Test Restaurant", dto.getRestaurantName());
        assertEquals("Test Category", dto.getCategoryName());
        assertEquals("Test Food", dto.getFoodName());
        assertEquals("Test Description", dto.getDescription());
        assertTrue(dto.getIsAvailable());
        assertEquals(new BigDecimal("9.99"), dto.getPrice());
        assertArrayEquals(new byte[]{1, 2, 3}, dto.getImageData());
    }

    /**
     * Tests the equals and hashCode methods of {@link MenuItemResponseDto}.
     */
    @Test
    public void testEqualsAndHashCode() {
        MenuItemResponseDto dto1 = new MenuItemResponseDto();
        dto1.setId(1);
        dto1.setRestaurantName("Test Restaurant");
        dto1.setCategoryName("Test Category");
        dto1.setFoodName("Test Food");
        dto1.setDescription("Test Description");
        dto1.setIsAvailable(true);
        dto1.setPrice(new BigDecimal("9.99"));
        dto1.setImageData(new byte[]{1, 2, 3});

        MenuItemResponseDto dto2 = new MenuItemResponseDto();
        dto2.setId(1);
        dto2.setRestaurantName("Test Restaurant");
        dto2.setCategoryName("Test Category");
        dto2.setFoodName("Test Food");
        dto2.setDescription("Test Description");
        dto2.setIsAvailable(true);
        dto2.setPrice(new BigDecimal("9.99"));
        dto2.setImageData(new byte[]{1, 2, 3});

        MenuItemResponseDto dto3 = new MenuItemResponseDto();
        dto3.setId(2);
        dto3.setRestaurantName("Different Restaurant");
        dto3.setCategoryName("Different Category");
        dto3.setFoodName("Different Food");
        dto3.setDescription("Different Description");
        dto3.setIsAvailable(false);
        dto3.setPrice(new BigDecimal("19.99"));
        dto3.setImageData(new byte[]{4, 5, 6});

        // Test equality
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // Test hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    /**
     * Tests the toString method of {@link MenuItemResponseDto}.
     */
    @Test
    public void testToString() {
        MenuItemResponseDto dto = new MenuItemResponseDto();
        dto.setId(1);
        dto.setRestaurantName("Test Restaurant");
        dto.setCategoryName("Test Category");
        dto.setFoodName("Test Food");
        dto.setDescription("Test Description");
        dto.setIsAvailable(true);
        dto.setPrice(new BigDecimal("9.99"));
        dto.setImageData(new byte[]{1, 2, 3});

        String expectedString = "MenuItemResponseDto{id=1, restaurantName='Test Restaurant', categoryName='Test Category', "
                +
                "foodName='Test Food', description='Test Description', isAvailable=true, price=9.99, imageData=[1, 2, 3]}";
        assertEquals(expectedString, dto.toString());
    }

    /**
     * Tests the getAvailable and setAvailable methods of {@link MenuItemResponseDto}.
     */
    @Test
    public void testGetAvailableAndSetAvailable() {
        MenuItemResponseDto dto = new MenuItemResponseDto();
        dto.setIsAvailable(true);
        assertTrue(dto.getIsAvailable());

        dto.setIsAvailable(false);
        assertFalse(dto.getIsAvailable());
    }
}
