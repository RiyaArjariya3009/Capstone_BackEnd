package com.restaurant.RestaurantMicroservice.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link CreateMenuItemRequestDto}.
 */
public class CreateMenuItemRequestDtoTest {

    /**
     * The DTO object used for testing.
     */
    private CreateMenuItemRequestDto menuItemDto;

    /**
     * Validator used to validate the DTO fields.
     */
    private Validator validator;

    /**
     * Setup method to initialize the DTO and validator before each test.
     */
    @BeforeEach
    public void setup() {
        // Initialize the DTO
        menuItemDto = new CreateMenuItemRequestDto();

        // Set up the Validator to check the constraints
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Tests the default constructor for initial values.
     */
    @Test
    public void testDefaultConstructor() {
        assertThat(menuItemDto.getRestaurantId()).isEqualTo(0); // default int value
        assertThat(menuItemDto.getCategoryId()).isEqualTo(0);
        assertThat(menuItemDto.getFoodName()).isNull();
        assertThat(menuItemDto.getDescription()).isNull();
        assertThat(menuItemDto.getPrice()).isNull();
    }

    /**
     * Tests setting a valid restaurant ID.
     */
    @Test
    public void testSetRestaurantIdValid() {
        int restaurantId = 1; // Local variable
        menuItemDto.setRestaurantId(restaurantId);
        assertThat(menuItemDto.getRestaurantId()).isEqualTo(restaurantId);
    }

    /**
     * Tests setting a valid category ID.
     */
    @Test
    public void testSetCategoryIdValid() {
        int categoryId = 1; // Local variable
        menuItemDto.setCategoryId(categoryId);
        assertThat(menuItemDto.getCategoryId()).isEqualTo(categoryId);
    }

    /**
     * Tests trimming and replacing extra spaces in the food name.
     */
    @Test
    public void testSetFoodNameTrimAndReplaceWhitespace() {
        String foodNameWithSpaces = "  Pasta     Bolognese  "; // Local variable
        menuItemDto.setFoodName(foodNameWithSpaces);
        assertThat(menuItemDto.getFoodName()).isEqualTo("Pasta Bolognese");
    }

    /**
     * Tests setting a null value for the food name.
     */
    @Test
    public void testSetFoodNameNullValue() {
        menuItemDto.setFoodName(null);
        assertThat(menuItemDto.getFoodName()).isNull();
    }

    /**
     * Tests setting a valid description.
     */
    @Test
    public void testSetDescriptionValid() {
        String description = "Delicious Italian dish"; // Local variable
        menuItemDto.setDescription(description);
        assertThat(menuItemDto.getDescription()).isEqualTo(description);
    }

    /**
     * Tests setting a valid price.
     */
    @Test
    public void testSetPriceValid() {
        BigDecimal price = new BigDecimal("15.50"); // Local variable
        menuItemDto.setPrice(price);
        assertThat(menuItemDto.getPrice()).isEqualTo(price);
    }

    // Additional test cases...

    /**
     * Tests validation of a blank food name.
     */
    @Test
    public void testFoodNameValidationBlank() {
        menuItemDto.setFoodName(""); // Local variable
        menuItemDto.setDescription("Delicious Italian dish");
        menuItemDto.setRestaurantId(1);
        menuItemDto.setCategoryId(2);
        menuItemDto.setPrice(new BigDecimal("15.50"));

        Set<ConstraintViolation<CreateMenuItemRequestDto>> violations = validator.validate(menuItemDto);
        assertThat(violations).hasSize(1);

        ConstraintViolation<CreateMenuItemRequestDto> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("Food name is required");
    }

    // Additional validation tests...
}
