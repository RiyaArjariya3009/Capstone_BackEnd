package com.restaurant.RestaurantMicroservice.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link CategoryResponseDto}.
 */
public class CategoryResponseDtoTest {

    /**
     * Tests the getters and setters of {@link CategoryResponseDto}.
     */
    @Test
    public void testGettersAndSetters() {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(1);
        dto.setName("Category Name");
        dto.setRestaurantId(101);

        assertEquals(1, dto.getId(), "ID should be 1");
        assertEquals("Category Name", dto.getName(), "Name should be 'Category Name'");
        assertEquals(101, dto.getRestaurantId(), "Restaurant ID should be 101");
    }

    /**
     * Tests the {@code equals} and {@code hashCode} methods of {@link CategoryResponseDto}.
     */
    @Test
    public void testEqualsAndHashCode() {
        CategoryResponseDto dto1 = new CategoryResponseDto();
        dto1.setId(1);
        dto1.setName("Category Name");
        dto1.setRestaurantId(101);

        CategoryResponseDto dto2 = new CategoryResponseDto();
        dto2.setId(1);
        dto2.setName("Category Name");
        dto2.setRestaurantId(101);

        CategoryResponseDto dto3 = new CategoryResponseDto();
        dto3.setId(2);
        dto3.setName("Different Name");
        dto3.setRestaurantId(102);

        // Test equality
        assertEquals(dto1, dto2, "dto1 should be equal to dto2");
        assertNotEquals(dto1, dto3, "dto1 should not be equal to dto3");

        // Test hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode(), "Hash codes of dto1 and dto2 should be equal");
        assertNotEquals(dto1.hashCode(), dto3.hashCode(), "Hash codes of dto1 and dto3 should not be equal");
    }

    /**
     * Tests the {@code toString} method of {@link CategoryResponseDto}.
     */
    @Test
    public void testToString() {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(1);
        dto.setName("Category Name");
        dto.setRestaurantId(101);

        String expectedString = "CategoryResponseDto{id=1, name='Category Name', restaurantId=101}";
        assertEquals(expectedString, dto.toString(), "toString() method should return the expected string");
    }
}
