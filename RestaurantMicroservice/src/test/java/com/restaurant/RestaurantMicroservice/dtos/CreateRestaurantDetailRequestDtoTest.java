package com.restaurant.RestaurantMicroservice.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link CreateRestaurantDetailRequestDto} class.
 * These tests cover various scenarios including default values, setter methods, validation constraints,
 * and the methods equals(), hashCode(), and toString().
 */
public class CreateRestaurantDetailRequestDtoTest {

    /**
     * The instance of {@link CreateRestaurantDetailRequestDto} used in the tests.
     */
    private CreateRestaurantDetailRequestDto restaurantDetailDto;

    /**
     * The {@link Validator} instance used to validate the DTO.
     */
    private Validator validator;

    /**
     * Sets up the test environment by initializing the DTO and Validator.
     */
    @BeforeEach
    public void setup() {
        // Initialize the DTO
        restaurantDetailDto = new CreateRestaurantDetailRequestDto();

        // Set up the Validator to check the constraints
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Tests the default constructor values of the DTO.
     */
    @Test
    public void testDefaultConstructor() {
        assertThat(restaurantDetailDto.getUserId()).isEqualTo(0); // default int value
        assertThat(restaurantDetailDto.getRestaurantName()).isNull();
        assertThat(restaurantDetailDto.getAddress()).isNull();
        assertThat(restaurantDetailDto.getContactInformation()).isNull();
        assertThat(restaurantDetailDto.getOpeningHours()).isNull();
        assertThat(restaurantDetailDto.getDescription()).isNull();
    }

    /**
     * Tests setting and trimming whitespace in the restaurant name.
     */
    @Test
    public void testSetRestaurantNameTrimAndReplaceWhitespace() {
        restaurantDetailDto.setRestaurantName("  Good    Restaurant   ");
        assertThat(restaurantDetailDto.getRestaurantName()).isEqualTo("Good Restaurant");
    }

    /**
     * Tests setting the restaurant name to null.
     */
    @Test
    public void testSetRestaurantNameNullValue() {
        restaurantDetailDto.setRestaurantName(null);
        assertThat(restaurantDetailDto.getRestaurantName()).isNull();
    }

    /**
     * Tests setting valid contact information.
     */
    @Test
    public void testSetContactInformationValid() {
        restaurantDetailDto.setContactInformation("9861234567");
        assertThat(restaurantDetailDto.getContactInformation()).isEqualTo("9861234567");
    }

    /**
     * Tests setting a valid address.
     */
    @Test
    public void testSetAddressValid() {
        restaurantDetailDto.setAddress("123 Main St, City");
        assertThat(restaurantDetailDto.getAddress()).isEqualTo("123 Main St, City");
    }

    /**
     * Tests setting valid opening hours.
     */
    @Test
    public void testSetOpeningHoursValid() {
        restaurantDetailDto.setOpeningHours("10:00 AM - 10:00 PM");
        assertThat(restaurantDetailDto.getOpeningHours()).isEqualTo("10:00 AM - 10:00 PM");
    }

    /**
     * Tests setting a valid description.
     */
    @Test
    public void testSetDescriptionValid() {
        restaurantDetailDto.setDescription("A cozy place to enjoy food.");
        assertThat(restaurantDetailDto.getDescription()).isEqualTo("A cozy place to enjoy food.");
    }

    // Validation Tests

    /**
     * Tests validation when the user ID is zero.
     */
    @Test
    public void testUserIdValidationNull() {
        restaurantDetailDto.setUserId(0); // Default value, might be invalid depending on how zero is treated
        restaurantDetailDto.setRestaurantName("Test Restaurant");
        restaurantDetailDto.setAddress("123 Main St");
        restaurantDetailDto.setContactInformation("9861234567");
        restaurantDetailDto.setOpeningHours("9:00 AM - 9:00 PM");
        restaurantDetailDto.setDescription("Test Description");

        Set<ConstraintViolation<CreateRestaurantDetailRequestDto>> violations = validator.validate(restaurantDetailDto);
        assertThat(violations).isNotEmpty();
    }

    /**
     * Tests validation for restaurant name length.
     */
    @Test
    public void testRestaurantNameValidationLength() {
        restaurantDetailDto.setRestaurantName("Jo");
        restaurantDetailDto.setAddress("123 Main St");
        restaurantDetailDto.setContactInformation("9861234567");
        restaurantDetailDto.setOpeningHours("9:00 AM - 9:00 PM");
        restaurantDetailDto.setDescription("Test Description");

        Set<ConstraintViolation<CreateRestaurantDetailRequestDto>> violations = validator.validate(restaurantDetailDto);
        assertThat(violations).hasSize(1);
        ConstraintViolation<CreateRestaurantDetailRequestDto> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("Name must be at least 3 characters long");
    }

    /**
     * Tests validation for invalid characters in restaurant name.
     */
    @Test
    public void testRestaurantNameValidationInvalidCharacters() {
        restaurantDetailDto.setRestaurantName("Test123");
        restaurantDetailDto.setAddress("123 Main St");
        restaurantDetailDto.setContactInformation("9861234567");
        restaurantDetailDto.setOpeningHours("9:00 AM - 9:00 PM");
        restaurantDetailDto.setDescription("Test Description");

        Set<ConstraintViolation<CreateRestaurantDetailRequestDto>> violations = validator.validate(restaurantDetailDto);
        assertThat(violations).hasSize(1);
        ConstraintViolation<CreateRestaurantDetailRequestDto> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("Name must contain only alphabetic characters");
    }

    // Test equals() and hashCode()

    /**
     * Tests the equals() method when the DTOs have the same values.
     */
    @Test
    public void testEqualsSameValues() {
        restaurantDetailDto.setUserId(1);
        restaurantDetailDto.setRestaurantName("Good Restaurant");
        restaurantDetailDto.setAddress("123 Main St");
        restaurantDetailDto.setContactInformation("9861234567");
        restaurantDetailDto.setOpeningHours("9:00 AM - 9:00 PM");
        restaurantDetailDto.setDescription("A cozy place");

        CreateRestaurantDetailRequestDto anotherDto = new CreateRestaurantDetailRequestDto();
        anotherDto.setUserId(1);
        anotherDto.setRestaurantName("Good Restaurant");
        anotherDto.setAddress("123 Main St");
        anotherDto.setContactInformation("9861234567");
        anotherDto.setOpeningHours("9:00 AM - 9:00 PM");
        anotherDto.setDescription("A cozy place");

        assertThat(restaurantDetailDto).isEqualTo(anotherDto);
        assertThat(restaurantDetailDto.hashCode()).isEqualTo(anotherDto.hashCode());
    }

    /**
     * Tests the equals() method when the DTOs have different values.
     */
    @Test
    public void testEqualsDifferentValues() {
        restaurantDetailDto.setUserId(1);
        restaurantDetailDto.setRestaurantName("Good Restaurant");
        restaurantDetailDto.setAddress("123 Main St");
        restaurantDetailDto.setContactInformation("9861234567");
        restaurantDetailDto.setOpeningHours("9:00 AM - 9:00 PM");
        restaurantDetailDto.setDescription("A cozy place");

        CreateRestaurantDetailRequestDto anotherDto = new CreateRestaurantDetailRequestDto();
        anotherDto.setUserId(2); // Different user ID
        anotherDto.setRestaurantName("Good Restaurant");
        anotherDto.setAddress("123 Main St");
        anotherDto.setContactInformation("9861234567");
        anotherDto.setOpeningHours("9:00 AM - 9:00 PM");
        anotherDto.setDescription("A cozy place");

        assertThat(restaurantDetailDto).isNotEqualTo(anotherDto);
    }

    /**
     * Tests the toString() method of the DTO.
     */
    @Test
    public void testToString() {
        restaurantDetailDto.setUserId(1);
        restaurantDetailDto.setRestaurantName("Good Restaurant");
        restaurantDetailDto.setAddress("123 Main St");
        restaurantDetailDto.setContactInformation("9861234567");
        restaurantDetailDto.setOpeningHours("9:00 AM - 9:00 PM");
        restaurantDetailDto.setDescription("A cozy place");

        String expected =
                "CreateRestaurantDetailRequestDto{userId=1, "
                        +
                ""
                +
                "restaurantName='Good Restaurant', address='123 Main St', contactInformation='9861234567',"
                        +
                        " openingHours='9:00 AM - 9:00 PM', description='A cozy place'}";
        assertThat(restaurantDetailDto.toString()).isEqualTo(expected);
    }
}
