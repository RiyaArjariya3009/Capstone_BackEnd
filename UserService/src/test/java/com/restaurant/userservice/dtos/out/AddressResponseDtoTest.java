package com.restaurant.userservice.dtos.out;

import com.restaurant.userservice.dto.out.AddressResponseDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for AddressResponseDto.
 * This class contains test cases for the AddressResponseDto class.
 */
public class AddressResponseDtoTest {

    /**
     * Tests the default constructor of AddressResponseDto.
     */
    @Test
    public void testDefaultConstructor() {
        AddressResponseDto dto = new AddressResponseDto();
        assertNotNull(dto, "AddressResponseDto instance should not be null");
    }

    /**
     * Tests the parameterized constructor of AddressResponseDto.
     */
    @Test
    public void testParameterizedConstructor() {
        int addressId = 1;
        int userId = 100;
        String street = "123 Main St";
        String city = "Anytown";
        String state = "Anystate";
        String zipCode = "12345";
        String country = "Anyland";

        AddressResponseDto dto = new AddressResponseDto(
                addressId, userId, street, city, state, zipCode, country);

        assertNotNull(dto, "AddressResponseDto instance should not be null");
        assertEquals(addressId, dto.getAddressId(), "Address ID should match");
        assertEquals(userId, dto.getUserId(), "User ID should match");
        assertEquals(street, dto.getStreet(), "Street should match");
        assertEquals(city, dto.getCity(), "City should match");
        assertEquals(state, dto.getState(), "State should match");
        assertEquals(zipCode, dto.getZipCode(), "Zip code should match");
        assertEquals(country, dto.getCountry(), "Country should match");
    }

    /**
     * Tests the getter and setter methods of AddressResponseDto.
     */
    @Test
    public void testGetterAndSetter() {
        AddressResponseDto dto = new AddressResponseDto();
        int addressId = 1;
        int userId = 100;
        String street = "123 Main St";
        String city = "Anytown";
        String state = "Anystate";
        String zipCode = "12345";
        String country = "Anyland";

        dto.setAddressId(addressId);
        dto.setUserId(userId);
        dto.setStreet(street);
        dto.setCity(city);
        dto.setState(state);
        dto.setZipCode(zipCode);
        dto.setCountry(country);

        assertEquals(addressId, dto.getAddressId(), "Address ID should match");
        assertEquals(userId, dto.getUserId(), "User ID should match");
        assertEquals(street, dto.getStreet(), "Street should match");
        assertEquals(city, dto.getCity(), "City should match");
        assertEquals(state, dto.getState(), "State should match");
        assertEquals(zipCode, dto.getZipCode(), "Zip code should match");
        assertEquals(country, dto.getCountry(), "Country should match");
    }
}
