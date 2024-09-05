package com.restaurant.userservice.dtos.in;

import com.restaurant.userservice.dto.in.AddressRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link AddressRequestDto}.
 *
 * This class contains tests for validating the behavior of the {@link AddressRequestDto} class.
 * It covers the constructor, getters, setters, and validation logic of the DTO.
 */
public class AddressRequestDtoTest {

    /**
     * Validator instance used for validating the AddressRequestDto.
     */
    private Validator validator;

    /**
     * An instance of AddressRequestDto used in the test cases.
     */
    private AddressRequestDto addressRequestDto;

    /**
     * Sets up the test environment before each test case.
     * Initializes the Validator and AddressRequestDto with sample data.
     */
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        addressRequestDto = new AddressRequestDto("123 Main St", "Springfield", "IL", "62701", "USA");
    }

    /**
     * Tests the constructor of AddressRequestDto to ensure it initializes fields correctly.
     */
    @Test
    public void testAddressRequestDtoConstructor() {
        AddressRequestDto testAddressRequestDto = new AddressRequestDto("123 Main St", "Springfield", "IL", "62701", "USA");

        assertEquals("123 Main St", testAddressRequestDto.getStreet());
        assertEquals("Springfield", testAddressRequestDto.getCity());
        assertEquals("IL", testAddressRequestDto.getState());
        assertEquals("62701", testAddressRequestDto.getZipCode());
        assertEquals("USA", testAddressRequestDto.getCountry());
    }

    /**
     * Tests the getters and setters of AddressRequestDto to ensure they work as expected.
     */
    @Test
    public void testGettersAndSetters() {
        addressRequestDto.setId(1);
        assertEquals(1, addressRequestDto.getId());

        addressRequestDto.setStreet("456 Elm St");
        assertEquals("456 Elm St", addressRequestDto.getStreet());

        addressRequestDto.setCity("Metropolis");
        assertEquals("Metropolis", addressRequestDto.getCity());

        addressRequestDto.setState("NY");
        assertEquals("NY", addressRequestDto.getState());

        addressRequestDto.setZipCode("10001");
        assertEquals("10001", addressRequestDto.getZipCode());

        addressRequestDto.setCountry("Canada");
        assertEquals("Canada", addressRequestDto.getCountry());
    }

    /**
     * Tests the default constructor of AddressRequestDto to ensure it initializes fields to null.
     */
    @Test
    public void testDefaultConstructor() {
        AddressRequestDto defaultAddressRequestDto = new AddressRequestDto();

        assertNull(defaultAddressRequestDto.getStreet());
        assertNull(defaultAddressRequestDto.getCity());
        assertNull(defaultAddressRequestDto.getState());
        assertNull(defaultAddressRequestDto.getZipCode());
        assertNull(defaultAddressRequestDto.getCountry());
    }

    /**
     * Tests the validation of AddressRequestDto to ensure no validation errors occur with valid data.
     */
    @Test
    public void testValidationSuccess() {
        Set<ConstraintViolation<AddressRequestDto>> violations = validator.validate(addressRequestDto);
        assertTrue(violations.isEmpty(), "Expected no validation errors.");
    }

    /**
     * Tests the validation of AddressRequestDto to ensure appropriate errors occur with blank fields.
     */
    @Test
    public void testValidationFailureBlankFields() {
        AddressRequestDto invalidDto = new AddressRequestDto("", "", "", "", "");

        Set<ConstraintViolation<AddressRequestDto>> violations = validator.validate(invalidDto);

        assertEquals(5, violations.size());

        for (ConstraintViolation<AddressRequestDto> violation : violations) {
            String message = violation.getMessage();
            assertTrue(
                    message.equals("Street is mandatory")
                            ||
                            message.equals("City is mandatory")
                            ||
                            message.equals("State is mandatory")
                            ||
                            message.equals("Zip code is mandatory")
                            ||
                            message.equals("Country is mandatory"),
                    "Unexpected validation message: " + message
            );
        }
    }
}
