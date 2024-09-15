package com.restaurant.RestaurantMicroservice.dtos;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test class for {@link MenuItemUpdateResponseDto}.
 * This class tests the getters, setters, equals, hashCode, and toString methods of the DTO.
 */
public class MenuItemUpdateResponseDtoTest {

    /**
     * Test to verify the proper functioning of the getters and setters
     * of the {@link MenuItemUpdateResponseDto}.
     */
    @Test
    public void testGettersAndSetters() {
        MenuItemUpdateResponseDto dto = new MenuItemUpdateResponseDto();
        BigDecimal price = new BigDecimal("19.99");

        dto.setName("Test Dish");
        dto.setPrice(price);
        dto.setDescription("Delicious test dish");
        dto.setCategoryName("Test Category");
        dto.setMessage("Update successful");

        assertEquals("Test Dish", dto.getName());
        assertEquals(price, dto.getPrice());
        assertEquals("Delicious test dish", dto.getDescription());
        assertEquals("Test Category", dto.getCategoryName());
        assertEquals("Update successful", dto.getMessage());
    }

    /**
     * Test to verify the equals and hashCode methods of {@link MenuItemUpdateResponseDto}.
     */
    @Test
    public void testEqualsAndHashCode() {
        MenuItemUpdateResponseDto dto1 = new MenuItemUpdateResponseDto();
        MenuItemUpdateResponseDto dto2 = new MenuItemUpdateResponseDto();
        MenuItemUpdateResponseDto dto3 = new MenuItemUpdateResponseDto();
        BigDecimal price = new BigDecimal("19.99");

        dto1.setName("Test Dish");
        dto1.setPrice(price);
        dto1.setDescription("Delicious test dish");
        dto1.setCategoryName("Test Category");
        dto1.setMessage("Update successful");

        dto2.setName("Test Dish");
        dto2.setPrice(price);
        dto2.setDescription("Delicious test dish");
        dto2.setCategoryName("Test Category");
        dto2.setMessage("Update successful");

        dto3.setName("Another Dish"); // Different name

        // Test equality
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // Test hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    /**
     * Test to verify the toString method of {@link MenuItemUpdateResponseDto}.
     */
    @Test
    public void testToString() {
        MenuItemUpdateResponseDto dto = new MenuItemUpdateResponseDto();
        BigDecimal price = new BigDecimal("19.99");

        dto.setName("Test Dish");
        dto.setPrice(price);
        dto.setDescription("Delicious test dish");
        dto.setCategoryName("Test Category");
        dto.setMessage("Update successful");

        String expectedString = "MenuItemUpdateResponseDto{"
                +
                "name='Test Dish'"
                +
                ", price=" + price
                +
                ", description='Delicious test dish'"
                +
                ", categoryName='Test Category'"
                +
                ", message='Update successful'"
                +
                '}';

        assertEquals(expectedString, dto.toString());
    }
}
