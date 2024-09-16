package com.restaurant.userservice.dtos.in;
import com.restaurant.userservice.dto.in.UserUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;


public final class  UserUpdateRequestDtoTest {

    /**
     * Test this for validation.
     */
    private Validator validator;

    /**
     * Sets up the validator before each test.
     * Subclasses can override this method to provide custom setup.
     */
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Test that a valid UserUpdateRequestDto does not produce any validation errors.
     */
    @Test
    public void testValidUserUpdateRequestDto() {
        UserUpdateRequestDto dto = new UserUpdateRequestDto();
        dto.setUsername("testuser");
        dto.setEmail("testuser@example.com");
        dto.setPassword("password123");
        dto.setContactNumber("1234567890");

        Set<ConstraintViolation<UserUpdateRequestDto>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty(), "Expected no validation errors for a valid DTO");
    }

    /**
     * Test that missing username produces a validation error.
     */
    @Test
    public void testMissingUsername() {
        UserUpdateRequestDto dto = new UserUpdateRequestDto();
        dto.setEmail("testuser@example.com");
        dto.setPassword("password123");
        dto.setContactNumber("1234567890");

        Set<ConstraintViolation<UserUpdateRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Expected validation errors due to missing username");
        assertEquals(1, violations.size(), "Expected one validation error");
        assertEquals("Username is mandatory", violations.iterator().next().getMessage());
    }

    /**
     * Test that an invalid email address produces a validation error.
     */
    @Test
    public void testInvalidEmail() {
        UserUpdateRequestDto dto = new UserUpdateRequestDto();
        dto.setUsername("testuser");
        dto.setEmail("invalid-email");
        dto.setPassword("password123");
        dto.setContactNumber("1234567890");

        Set<ConstraintViolation<UserUpdateRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Expected validation errors due to invalid email");
        assertEquals(1, violations.size(), "Expected one validation error");
        assertEquals("Email should be valid", violations.iterator().next().getMessage());
    }

    /**
     * Test that missing password produces a validation error.
     */
    @Test
    public void testMissingPassword() {
        UserUpdateRequestDto dto = new UserUpdateRequestDto();
        dto.setUsername("testuser");
        dto.setEmail("testuser@example.com");
        dto.setContactNumber("1234567890");

        Set<ConstraintViolation<UserUpdateRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Expected validation errors due to missing password");
        assertEquals(1, violations.size(), "Expected one validation error");
        assertEquals("Password is mandatory", violations.iterator().next().getMessage());
    }

    /**
     * Test that missing contact number produces a validation error.
     */
    @Test
    public void testMissingContactNumber() {
        UserUpdateRequestDto dto = new UserUpdateRequestDto();
        dto.setUsername("testuser");
        dto.setEmail("testuser@example.com");
        dto.setPassword("password123");

        Set<ConstraintViolation<UserUpdateRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Expected validation errors due to missing contact number");
        assertEquals(1, violations.size(), "Expected one validation error");
        assertEquals("Contact number is mandatory", violations.iterator().next().getMessage());
    }
}

