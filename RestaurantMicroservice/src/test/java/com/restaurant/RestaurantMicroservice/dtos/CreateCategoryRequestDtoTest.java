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
 * Unit test class for {@link CreateCategoryRequestDto}.
 * This class tests the functionality of the CreateCategoryRequestDto object, including getter/setter methods,
 * equality, hashCode, and validation logic.
 */
public class CreateCategoryRequestDtoTest {

    /**
     * The DTO object to be tested.
     */
    private CreateCategoryRequestDto categoryDto;

    /**
     * Validator instance used for validating the constraints on the DTO.
     */
    private Validator validator;

    /**
     * Sets up the DTO and Validator before each test is executed.
     */
    @BeforeEach
    public void setup() {
        // Initialize the DTO
        categoryDto = new CreateCategoryRequestDto();

        // Set up the Validator to check the constraints
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Tests the default constructor, ensuring the name is null and restaurantId is initialized to 0.
     */
    @Test
    public void testDefaultConstructor() {
        assertThat(categoryDto.getName()).isNull();
        assertThat(categoryDto.getRestaurantId()).isEqualTo(0); // default int value
    }

    /**
     * Tests the setter for the name field with a valid name value.
     */
   /* @Test
    public void testSetNameValid() {
        categoryDto.setName("Asian Cuisine");
        assertThat(categoryDto.getName()).isEqualTo("Asian Cuisine");
    }*/

    /**
     * Tests the setter for the name field by trimming and replacing extra spaces within the name.
     */
    /*@Test
    public void testSetNameTrimAndReplaceWhitespace() {
        categoryDto.setName("  Asian     Cuisine  ");
        assertThat(categoryDto.getName()).isEqualTo("Asian Cuisine");
    }*/

    /**
     * Tests the setter for the name field when passing a null value.
     */
    @Test
    public void testSetNameNullValue() {
        categoryDto.setName(null);
        assertThat(categoryDto.getName()).isNull();
    }

    /**
     * Tests the setter for the restaurantId field with a valid value.
     */
    @Test
    public void testSetRestaurantIdValid() {
        categoryDto.setRestaurantId(100);
        assertThat(categoryDto.getRestaurantId()).isEqualTo(100);
    }

    /**
     * Tests the equals method when comparing the object to itself.
     */
    @Test
    public void testEqualsSameObject() {
        assertThat(categoryDto.equals(categoryDto)).isTrue();
    }

    /**
     * Tests the equals method when comparing two objects with the same field values.
     */
    @Test
    public void testEqualsDifferentObjectSameValues() {
        categoryDto.setName("Asian Cuisine");
        categoryDto.setRestaurantId(1);

        CreateCategoryRequestDto categoryDto2 = new CreateCategoryRequestDto();
        categoryDto2.setName("Asian Cuisine");
        categoryDto2.setRestaurantId(1);

        assertThat(categoryDto).isEqualTo(categoryDto2);
    }

    /**
     * Tests the equals method when comparing two objects with different field values.
     */
    @Test
    public void testEqualsDifferentValues() {
        categoryDto.setName("Asian Cuisine");
        categoryDto.setRestaurantId(1);

        CreateCategoryRequestDto categoryDto2 = new CreateCategoryRequestDto();
        categoryDto2.setName("Mexican Cuisine");
        categoryDto2.setRestaurantId(2);

        assertThat(categoryDto).isNotEqualTo(categoryDto2);
    }

    /**
     * Tests the hashCode method when two objects have the same field values.
     */
    @Test
    public void testHashCodeSameValues() {
        categoryDto.setName("Asian Cuisine");
        categoryDto.setRestaurantId(1);

        CreateCategoryRequestDto categoryDto2 = new CreateCategoryRequestDto();
        categoryDto2.setName("Asian Cuisine");
        categoryDto2.setRestaurantId(1);

        assertThat(categoryDto.hashCode()).isEqualTo(categoryDto2.hashCode());
    }

    /**
     * Tests the hashCode method when two objects have different field values.
     */
    @Test
    public void testHashCodeDifferentValues() {
        categoryDto.setName("Asian Cuisine");
        categoryDto.setRestaurantId(1);

        CreateCategoryRequestDto categoryDto2 = new CreateCategoryRequestDto();
        categoryDto2.setName("Mexican Cuisine");
        categoryDto2.setRestaurantId(2);

        assertThat(categoryDto.hashCode()).isNotEqualTo(categoryDto2.hashCode());
    }

    /**
     * Tests the toString method to ensure it returns the correct string representation of the DTO.
     */
    /*@Test
    public void testToString() {
        categoryDto.setName("Asian Cuisine");
        categoryDto.setRestaurantId(1);

        String expectedString = "CreateCategoryRequestDto{name='Asian Cuisine', restaurantId=1}";
        assertThat(categoryDto.toString()).isEqualTo(expectedString);
    }*/

    /**
     * Tests validation of the name field when it is blank.
     * Verifies that a constraint violation occurs and returns the correct validation message.
     */
    @Test
    public void testNameValidationBlankName() {
        categoryDto.setName("");
        categoryDto.setRestaurantId(1);

        Set<ConstraintViolation<CreateCategoryRequestDto>> violations = validator.validate(categoryDto);
        assertThat(violations).hasSize(1);

        ConstraintViolation<CreateCategoryRequestDto> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("Category name is required");
    }

    /**
     * Tests validation of the name field when it contains a valid name.
     * Verifies that no constraint violations occur.
     */
    @Test
    public void testNameValidationValidName() {
        categoryDto.setName("Asian Cuisine");
        categoryDto.setRestaurantId(1);

        Set<ConstraintViolation<CreateCategoryRequestDto>> violations = validator.validate(categoryDto);
        assertThat(violations).isEmpty();
    }

    /**
     * Tests validation of the restaurantId field when it contains a valid value.
     * Verifies that no constraint violations occur.
     */
    @Test
    public void testRestaurantIdValidationValidRestaurantId() {
        categoryDto.setName("Asian Cuisine");
        categoryDto.setRestaurantId(100);

        Set<ConstraintViolation<CreateCategoryRequestDto>> violations = validator.validate(categoryDto);
        assertThat(violations).isEmpty();
    }
}
