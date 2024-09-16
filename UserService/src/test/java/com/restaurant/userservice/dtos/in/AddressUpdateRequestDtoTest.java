package com.restaurant.userservice.dtos.in;

import com.restaurant.userservice.dto.in.AddressUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link AddressUpdateRequestDto}.
 *
 * This class contains tests for validating the behavior of the {@link AddressUpdateRequestDto} class.
 * It covers validation scenarios for missing fields and checks the correct behavior of setters and getters.
 */
public class AddressUpdateRequestDtoTest {

    /**
     * Validator instance used for validating AddressUpdateRequestDto.
     */
    private Validator validator;

    /**
     * Sets up the test environment before each test case.
     * Initializes the Validator instance.
     */
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Tests validation of AddressUpdateRequestDto with no errors.
     * Verifies that the DTO is valid when all required fields are provided.
     */
    @Test
    public void testValidationNoErrors() {
        AddressUpdateRequestDto dto = new AddressUpdateRequestDto();
        dto.setStreet("123 Main St");
        dto.setCity("Springfield");
        dto.setState("IL");
        dto.setZipCode("62701");
        dto.setCountry("USA");

        Set<ConstraintViolation<AddressUpdateRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    /**
     * Tests validation of AddressUpdateRequestDto when the street field is missing.
     * Verifies that a validation error is returned for the missing street.
     */
    @Test
    public void testValidationMissingStreet() {
        AddressUpdateRequestDto dto = new AddressUpdateRequestDto();
        dto.setCity("Springfield");
        dto.setState("IL");
        dto.setZipCode("62701");
        dto.setCountry("USA");

        Set<ConstraintViolation<AddressUpdateRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation errors");
        assertEquals("Street is mandatory", violations.iterator().next().getMessage());
    }

    /**
     * Tests validation of AddressUpdateRequestDto when the city field is missing.
     * Verifies that a validation error is returned for the missing city.
     */
    @Test
    public void testValidationMissingCity() {
        AddressUpdateRequestDto dto = new AddressUpdateRequestDto();
        dto.setStreet("123 Main St");
        dto.setState("IL");
        dto.setZipCode("62701");
        dto.setCountry("USA");

        Set<ConstraintViolation<AddressUpdateRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation errors");
        assertEquals("City is mandatory", violations.iterator().next().getMessage());
    }

    /**
     * Tests validation of AddressUpdateRequestDto when the state field is missing.
     * Verifies that a validation error is returned for the missing state.
     */
    @Test
    public void testValidationMissingState() {
        AddressUpdateRequestDto dto = new AddressUpdateRequestDto();
        dto.setStreet("123 Main St");
        dto.setCity("Springfield");
        dto.setZipCode("62701");
        dto.setCountry("USA");

        Set<ConstraintViolation<AddressUpdateRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation errors");
        assertEquals("State is mandatory", violations.iterator().next().getMessage());
    }

    /**
     * Tests validation of AddressUpdateRequestDto when the zip code field is missing.
     * Verifies that a validation error is returned for the missing zip code.
     */
    @Test
    public void testValidationMissingZipCode() {
        AddressUpdateRequestDto dto = new AddressUpdateRequestDto();
        dto.setStreet("123 Main St");
        dto.setCity("Springfield");
        dto.setState("IL");
        dto.setCountry("USA");

        Set<ConstraintViolation<AddressUpdateRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation errors");
        assertEquals("Zip code is mandatory", violations.iterator().next().getMessage());
    }

    /**
     * Tests validation of AddressUpdateRequestDto when the country field is missing.
     * Verifies that a validation error is returned for the missing country.
     */
    @Test
    public void testValidationMissingCountry() {
        AddressUpdateRequestDto dto = new AddressUpdateRequestDto();
        dto.setStreet("123 Main St");
        dto.setCity("Springfield");
        dto.setState("IL");
        dto.setZipCode("62701");

        Set<ConstraintViolation<AddressUpdateRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation errors");
        assertEquals("Country is mandatory", violations.iterator().next().getMessage());
    }

    /**
     * Tests the setters and getters of AddressUpdateRequestDto to ensure they work correctly.
     * Verifies that setters update the values and getters return the correct values.
     */
    @Test
    public void testSettersAndGetters() {
        AddressUpdateRequestDto dto = new AddressUpdateRequestDto();

        // Set values using setters
        dto.setStreet("123 Main St");
        dto.setCity("Springfield");
        dto.setState("IL");
        dto.setZipCode("62701");
        dto.setCountry("USA");

        // Verify that getters return the values set by setters
        assertEquals("123 Main St", dto.getStreet());
        assertEquals("Springfield", dto.getCity());
        assertEquals("IL", dto.getState());
        assertEquals("62701", dto.getZipCode());
        assertEquals("USA", dto.getCountry());
    }
}
