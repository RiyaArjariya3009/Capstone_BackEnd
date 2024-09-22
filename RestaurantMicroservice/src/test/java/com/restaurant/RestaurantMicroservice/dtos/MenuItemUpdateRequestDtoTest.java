package com.restaurant.RestaurantMicroservice.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link MenuItemUpdateRequestDto}.
 * <p>
 * This class tests the validation constraints for the {@link MenuItemUpdateRequestDto} class,
 * including name, price, description, and category ID constraints.
 * </p>
 */
public class MenuItemUpdateRequestDtoTest {
    /**
     * Validator instance used for validating {@link MenuItemUpdateRequestDto} objects.
     */

    private Validator validator;

    /**
     * Sets up the validator before each test.
     */
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Tests the validation of a {@link MenuItemUpdateRequestDto} instance with valid data.
     */
    @Test
    void testValidMenuItemUpdateRequestDto() {
        MenuItemUpdateRequestDto dto = new MenuItemUpdateRequestDto();
        dto.setName("Valid Name");
        dto.setPrice(BigDecimal.valueOf(10.0));
        dto.setDescription("A valid description.");
        dto.setCategoryId(1);

        Set<ConstraintViolation<MenuItemUpdateRequestDto>> violations = validator.validate(dto);

        assertEquals(0, violations.size(), "Expected no constraint violations");
    }

    /**
     * Tests the validation of the name field for {@link MenuItemUpdateRequestDto}.
     * <p>
     * This test verifies that a name longer than 100 characters is not allowed.
     * </p>
     */
    @Test
    void testNameValidation() {
        MenuItemUpdateRequestDto dto = new MenuItemUpdateRequestDto();

        // Invalid name (too long)
        String longName = new String(new char[101]).replace('\0', 'a'); // Java 8 equivalent of .repeat()
        dto.setName(longName); // Name longer than 100 characters (invalid)

        // Set valid values for other fields
        dto.setPrice(BigDecimal.valueOf(10.0)); // Valid price
        dto.setDescription("A valid description."); // Valid description
        dto.setCategoryId(1); // Valid category ID

        Set<ConstraintViolation<MenuItemUpdateRequestDto>> violations = validator.validate(dto);

        // Expecting 1 validation error, related to the name field
        assertEquals(1, violations.size());

        // Check that the message corresponds to the name validation error
        ConstraintViolation<MenuItemUpdateRequestDto> violation = violations.iterator().next();
        assertEquals("Name must be between 3 and 100 characters", violation.getMessage());
    }

    /**
     * Tests the validation of the price field for {@link MenuItemUpdateRequestDto}.
     * <p>
     * This test verifies that a null price is not allowed.
     * </p>
     */
    @Test
    void testPriceValidation() {
        MenuItemUpdateRequestDto dto = new MenuItemUpdateRequestDto();
        dto.setName("Valid Name");
        dto.setPrice(null); // Invalid price
        dto.setDescription("A valid description.");
        dto.setCategoryId(1);

        Set<ConstraintViolation<MenuItemUpdateRequestDto>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Price cannot be null", violations.iterator().next().getMessage());
    }

    /**
     * Tests the validation of the description field for {@link MenuItemUpdateRequestDto}.
     * <p>
     * This test verifies that a blank description is not allowed.
     * </p>
     */
    @Test
    void testDescriptionValidation() {
        MenuItemUpdateRequestDto dto = new MenuItemUpdateRequestDto();
        dto.setName("Valid Name");
        dto.setPrice(BigDecimal.valueOf(10.0));
        dto.setDescription(""); // Invalid description
        dto.setCategoryId(1);

        Set<ConstraintViolation<MenuItemUpdateRequestDto>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Description cannot be blank", violations.iterator().next().getMessage());
    }

    /**
     * Tests the validation of the categoryId field for {@link MenuItemUpdateRequestDto}.
     * <p>
     * This test verifies that a non-positive category ID is not allowed.
     * </p>
     */
   /* @Test
    void testCategoryIdValidation() {
        MenuItemUpdateRequestDto dto = new MenuItemUpdateRequestDto();
        dto.setName("Valid Name");
        dto.setPrice(BigDecimal.valueOf(10.0));
        dto.setDescription("A valid description.");
        dto.setCategoryId(0); // Invalid categoryId

        Set<ConstraintViolation<MenuItemUpdateRequestDto>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Category ID must be a positive integer", violations.iterator().next().getMessage());
    }*/

    /**
     * Tests the validation of the name field for {@link MenuItemUpdateRequestDto} with a name that is too long.
     * <p>
     * This test verifies that a name longer than 100 characters is not allowed.
     * </p>
     */
    @Test
    void testNameTooLong() {
        MenuItemUpdateRequestDto dto = new MenuItemUpdateRequestDto();
        String longName = new String(new char[101]).replace('\0', 'a'); // Java 8 workaround to repeat 'a' 101 times
        dto.setName(longName); // Name too long
        dto.setPrice(BigDecimal.valueOf(10.0));
        dto.setDescription("A valid description.");
        dto.setCategoryId(1);

        Set<ConstraintViolation<MenuItemUpdateRequestDto>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Name must be between 3 and 100 characters", violations.iterator().next().getMessage());
    }
}
