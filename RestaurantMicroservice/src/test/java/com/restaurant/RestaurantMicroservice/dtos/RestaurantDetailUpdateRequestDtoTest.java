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
import org.springframework.mock.web.MockMultipartFile;
/**
 * Unit tests for {@link RestaurantDetailUpdateRequestDto}.
 */
public class RestaurantDetailUpdateRequestDtoTest {

    /**
     *  validator variable before each test.
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
     * Tests that a valid DTO does not produce any constraint violations.
     */

    @Test
    public void testValidDto() {
        // Create a valid instance of the DTO
        RestaurantDetailUpdateRequestDto dto = new RestaurantDetailUpdateRequestDto();
        dto.setRestaurantName("Valid Restaurant");
        dto.setAddress("123 Valid Street");
        dto.setOpeningHours("9 AM - 10 PM");
        dto.setContactInformation("9861234567");
        dto.setImage(new MockMultipartFile("file", "image.jpg", "image/jpeg", new byte[10]));
        dto.setDescription("A valid description of the restaurant.");

        Set<ConstraintViolation<RestaurantDetailUpdateRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "DTO should be valid");
    }

  /*  @Test
    public void testRestaurantNameBlank() {
        RestaurantDetailUpdateRequestDto dto = new RestaurantDetailUpdateRequestDto();
        dto.setRestaurantName("   "); // Blank after trimming
        dto.setAddress("123 Valid Street");
        dto.setOpeningHours("9 AM - 10 PM");
        dto.setContactInformation("9861234567");
     //   dto.setImage(new byte[10]); // Ensure you use the correct field name
        dto.setImage(new MockMultipartFile("file", "image.jpg", "image/jpeg", new byte[10]));
        dto.setDescription("A valid description of the restaurant.");

        Set<ConstraintViolation<RestaurantDetailUpdateRequestDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Restaurant name cannot be blank", violations.iterator().next().getMessage());
    }*/
   /* @Test
    public void testRestaurantNameBlank() {
        RestaurantDetailUpdateRequestDto dto = new RestaurantDetailUpdateRequestDto();
        dto.setRestaurantName("   "); // Blank after trimming
        dto.setAddress("123 Valid Street");
        dto.setOpeningHours("9 AM - 10 PM");
        dto.setContactInformation("9861234567");
        dto.setImage(new MockMultipartFile("file", "image.jpg", "image/jpeg", new byte[10]));
        dto.setDescription("A valid description of the restaurant.");

        Set<ConstraintViolation<RestaurantDetailUpdateRequestDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Restaurant name cannot be blank", violations.iterator().next().getMessage());
    }*/
    /**
     * Tests that a DTO with an invalid restaurant name pattern results in a constraint violation.
     */

    @Test
    public void testRestaurantNamePattern() {
        RestaurantDetailUpdateRequestDto dto = new RestaurantDetailUpdateRequestDto();
        dto.setRestaurantName("123 Restaurant"); // Invalid characters (numbers)
        dto.setAddress("123 Valid Street");
        dto.setOpeningHours("9 AM - 10 PM");
        dto.setContactInformation("9861234567");
        dto.setImage(new MockMultipartFile("file", "image.jpg", "image/jpeg", new byte[10]));
        dto.setDescription("A valid description of the restaurant.");

        Set<ConstraintViolation<RestaurantDetailUpdateRequestDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Restaurant name must contain only alphabetic"
                +
                " characters and spaces", violations.iterator().next().getMessage());
    }


    /**
     * Tests that a DTO with invalid contact information results in a constraint violation.
     */
    @Test
    public void testContactInformationInvalid() {
        RestaurantDetailUpdateRequestDto dto = new RestaurantDetailUpdateRequestDto();
        dto.setRestaurantName("Valid Restaurant");
        dto.setAddress("123 Valid Street");
        dto.setOpeningHours("9 AM - 10 PM");
        dto.setContactInformation("1234567890"); // Invalid contact information
        dto.setImage(new MockMultipartFile("file", "image.jpg", "image/jpeg", new byte[10]));
        dto.setDescription("A valid description of the restaurant.");

        Set<ConstraintViolation<RestaurantDetailUpdateRequestDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Contact information must be exactly 10 digits and start with '9', '8', or '6'",
                violations.iterator().next().getMessage());
    }
    /**
     * Tests that a DTO with a null image results in a constraint violation.
     */

    @Test
    public void testImageNotNull() {
        RestaurantDetailUpdateRequestDto dto = new RestaurantDetailUpdateRequestDto();
        dto.setRestaurantName("Valid Restaurant");
        dto.setAddress("123 Valid Street");
        dto.setOpeningHours("9 AM - 10 PM");
        dto.setContactInformation("9861234567");
        dto.setImage(null); // Image is null
        dto.setDescription("A valid description of the restaurant.");

        Set<ConstraintViolation<RestaurantDetailUpdateRequestDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Image is required", violations.iterator().next().getMessage());
    }
    /**
     * Tests that a DTO with a description that is too long results in a constraint violation.
     */

    @Test
    public void testDescriptionTooLong() {
        RestaurantDetailUpdateRequestDto dto = new RestaurantDetailUpdateRequestDto();
        dto.setRestaurantName("Valid Restaurant");
        dto.setAddress("123 Valid Street");
        dto.setOpeningHours("9 AM - 10 PM");
        dto.setContactInformation("9861234567");

        // Create a string with 300 characters, using a loop
        StringBuilder longDescription = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            longDescription.append("A");
        }

        dto.setImage(new MockMultipartFile("file", "image.jpg", "image/jpeg", new byte[10]));
        dto.setDescription(longDescription.toString()); // Description longer than 255 characters

        Set<ConstraintViolation<RestaurantDetailUpdateRequestDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Description must be less than 255 characters", violations.iterator().next().getMessage());
    }



}
