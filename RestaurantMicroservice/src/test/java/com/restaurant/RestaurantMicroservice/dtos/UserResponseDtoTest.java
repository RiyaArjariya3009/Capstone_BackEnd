package com.restaurant.RestaurantMicroservice.dtos;

import com.restaurant.RestaurantMicroservice.enums.RoleType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test class for {@link UserResponseDto}. It tests the functionality of the
 * getters, setters, equals, hashCode, and toString methods of the DTO class.
 */
public class UserResponseDtoTest {

    /**
     * Tests the getters and setters of {@link UserResponseDto}.
     * Ensures that values set via the setters are correctly retrieved using the getters.
     */
    @Test
    public void testGettersAndSetters() {
        UserResponseDto dto = new UserResponseDto();

        dto.setId(1);
        dto.setUsername("testUser");
        dto.setEmail("test@example.com");
        dto.setRole(RoleType.CUSTOMER); // Assuming RoleType has a CUSTOMER constant

        assertEquals(1, dto.getId());
        assertEquals("testUser", dto.getUsername());
        assertEquals("test@example.com", dto.getEmail());
        assertEquals(RoleType.CUSTOMER, dto.getRole());
    }

    /**
     * Tests the equality and hashCode implementation of {@link UserResponseDto}.
     * Verifies that two objects with the same values are considered equal
     * and have the same hashCode.
     */
    @Test
    public void testEqualsAndHashCode() {
        UserResponseDto dto1 = new UserResponseDto(1, "testUser", "test@example.com", RoleType.CUSTOMER);
        UserResponseDto dto2 = new UserResponseDto(1, "testUser", "test@example.com", RoleType.CUSTOMER);
        UserResponseDto dto3 = new UserResponseDto(2, "anotherUser",
                "another@example.com", RoleType.RESTAURANT_OWNER); // Assuming RoleType has a RESTAURANT_OWNER constant

        // Test equality
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // Test hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    /**
     * Tests the {@link UserResponseDto#toString()} method.
     * Ensures that the string representation of the object is as expected.
     */
    @Test
    public void testToString() {
        UserResponseDto dto = new UserResponseDto(1, "testUser", "test@example.com", RoleType.CUSTOMER);

        String expectedString = "UserResponseDto{"
                +
                "id="
                + dto.getId()
                +
                ", username='"
                + dto.getUsername()
                + '\''
                +
                ", email='" + dto.getEmail()
                + '\''
                +
                ", role=" + dto.getRole()
                +
                '}';

        assertEquals(expectedString, dto.toString());
    }
}
