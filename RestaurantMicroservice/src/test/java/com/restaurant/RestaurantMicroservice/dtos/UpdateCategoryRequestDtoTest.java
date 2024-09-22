package com.restaurant.RestaurantMicroservice.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link UpdateCategoryRequestDto}.
 */
public class UpdateCategoryRequestDtoTest {

    /**
     * Validator used for validating the DTO.
     */
    private Validator validator;

    /**
     * Sets up the validator before each test.
     */
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Tests that a valid category name does not produce any constraint violations.
     */
    @Test
    public void testValidCategoryName() {
        UpdateCategoryRequestDto dto = new UpdateCategoryRequestDto();
        dto.setName("Valid Category");

        Set<ConstraintViolation<UpdateCategoryRequestDto>> violations = validator.validate(dto);

        // Expect no violations
        assertTrue(violations.isEmpty(), "DTO should be valid");
    }

    /**
     * Tests that a blank category name results in a constraint violation.
     */
    @Test
    public void testCategoryNameBlank() {
        UpdateCategoryRequestDto dto = new UpdateCategoryRequestDto();
        dto.setName("   "); // Blank after trimming

        Set<ConstraintViolation<UpdateCategoryRequestDto>> violations = validator.validate(dto);

        // Check if violations contain the specific error we expect (NotBlank)
        boolean notBlankViolationFound = violations.stream()
                .anyMatch(violation -> violation.getMessage().equals("Category name cannot be blank"));

        assertTrue(notBlankViolationFound, "Expected NotBlank violation for blank name");

        // Optionally, you can print all violation messages to see what other constraints are being violated
        violations.forEach(violation -> System.out.println("Violation: " + violation.getMessage()));
    }

    /**
     * Tests that a category name that is too short results in a constraint violation.
     */
    @Test
    public void testCategoryNameTooShort() {
        UpdateCategoryRequestDto dto = new UpdateCategoryRequestDto();
        dto.setName("A"); // Too short

        Set<ConstraintViolation<UpdateCategoryRequestDto>> violations = validator.validate(dto);

        // Expect 1 violation due to name length being less than 3
        assertEquals(1, violations.size(), "Expected one violation due to name being too short");
        ConstraintViolation<UpdateCategoryRequestDto> violation = violations.iterator().next();
        assertEquals("Category name must be between 3 and 100 characters", violation.getMessage());
    }

    /**
     * Tests that a category name that is too long results in a constraint violation.
     */
    @Test
    public void testCategoryNameTooLong() {
        UpdateCategoryRequestDto dto = new UpdateCategoryRequestDto();
        String longName = new String(new char[101]).replace('\0', 'A'); // Name of 101 characters
        dto.setName(longName);

        Set<ConstraintViolation<UpdateCategoryRequestDto>> violations = validator.validate(dto);

        // Expect 1 violation due to name length exceeding 100 characters
        assertEquals(1, violations.size(), "Expected one violation due to name being too long");
        ConstraintViolation<UpdateCategoryRequestDto> violation = violations.iterator().next();
        assertEquals("Category name must be between 3 and 100 characters", violation.getMessage());
    }

    /**
     * Tests that a category name with invalid characters results in a constraint violation.
     */
    @Test
    public void testCategoryNameWithInvalidCharacters() {
        UpdateCategoryRequestDto dto = new UpdateCategoryRequestDto();
        dto.setName("Invalid#Name123");

        Set<ConstraintViolation<UpdateCategoryRequestDto>> violations = validator.validate(dto);

        // Expect 1 violation due to invalid characters
        assertEquals(1, violations.size(), "Expected one violation due to invalid characters in name");
        ConstraintViolation<UpdateCategoryRequestDto> violation = violations.iterator().next();
        assertEquals("Category name must contain only alphabetic characters and spaces", violation.getMessage());
    }
}
