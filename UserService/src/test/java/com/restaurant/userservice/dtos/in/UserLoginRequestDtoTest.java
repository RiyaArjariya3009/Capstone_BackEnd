package com.restaurant.userservice.dtos.in;

import com.restaurant.userservice.dto.in.UserLoginRequestDto;
import org.junit.jupiter.api.Test;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for {@link com.restaurant.userservice.dto.in.UserLoginRequestDto}.
 * Contains test cases to ensure that the validation constraints are correctly applied to the DTO.
 */
public class UserLoginRequestDtoTest {

    /**
     * ValidatorFactory instance used to create the {@link Validator} instance.
     * This is a setup and not a constant.
     */
    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();

    /**
     * Validator instance used to validate the {@link com.restaurant.userservice.dto.in.UserLoginRequestDto} objects.
     * This is a setup and not a constant.
     */
    private static final Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();

    /**
     * Tests that a valid {@link com.restaurant.userservice.dto.in.UserLoginRequestDto} passes validation without errors.
     */
    @Test
    public void testValidUserLoginRequestDto() {
        // Arrange
        UserLoginRequestDto dto = new UserLoginRequestDto("test@nucleusteq.com", "validPassword");

        // Act
        Set<javax.validation.ConstraintViolation<UserLoginRequestDto>> violations = VALIDATOR.validate(dto);

        // Assert
        assertTrue(violations.isEmpty(), "There should be no validation violations for a valid DTO.");
    }

    /**
     * Tests that an invalid email format in {@link UserLoginRequestDto} triggers a validation error.
     */
    @Test
    public void testInvalidEmail() {
        // Arrange
        UserLoginRequestDto dto = new UserLoginRequestDto("invalid-email", "validPassword");

        // Act
        Set<javax.validation.ConstraintViolation<UserLoginRequestDto>> violations = VALIDATOR.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "There should be validation violations for an invalid email.");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")
                        &&
                        v.getMessage().equals("Email should be valid")),
                "The email validation message should be 'Email should be valid'.");
    }

    /**
     * Tests that an email address not ending with '@nucleusteq.com' in {@link UserLoginRequestDto}
     * triggers a validation error.
     */
    @Test
    public void testEmailWithoutNucleusTeqDomain() {
        // Arrange
        UserLoginRequestDto dto = new UserLoginRequestDto("test@example.com", "validPassword");

        // Act
        Set<javax.validation.ConstraintViolation<UserLoginRequestDto>> violations = VALIDATOR.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "There should be validation violations for an email without 'nucleusteq.com' domain.");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")
                        &&
                        v.getMessage().equals("Email must be a valid mail address")),
                "The email validation message should be 'Email must be a valid mail address'.");
    }

    /**
     * Tests that a password shorter than 8 characters in {@link UserLoginRequestDto} triggers a validation error.
     */
    @Test
    public void testShortPassword() {
        // Arrange
        UserLoginRequestDto dto = new UserLoginRequestDto("test@nucleusteq.com", "short");

        // Act
        Set<javax.validation.ConstraintViolation<UserLoginRequestDto>> violations = VALIDATOR.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "There should be validation violations for a password shorter than 8 characters.");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")
                        &&
                        v.getMessage().equals("Password must be at least 8 characters long")),
                "The password validation message should be 'Password must be at least 8 characters long'.");
    }

    /**
     * Tests that a blank password in {@link UserLoginRequestDto} triggers a validation error.
     */
    @Test
    public void testBlankPassword() {
        // Arrange
        UserLoginRequestDto dto = new UserLoginRequestDto("test@nucleusteq.com", "");

        // Act
        Set<javax.validation.ConstraintViolation<UserLoginRequestDto>> violations = VALIDATOR.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "There should be validation violations for a blank password.");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")
                        &&
                        v.getMessage().equals("Password is mandatory")),
                "The password validation message should be 'Password is mandatory'.");
    }

    /**
     * Tests that a blank email in {@link UserLoginRequestDto} triggers a validation error.
     */
    @Test
    public void testBlankEmail() {
        // Arrange
        UserLoginRequestDto dto = new UserLoginRequestDto("", "validPassword");

        // Act
        Set<javax.validation.ConstraintViolation<UserLoginRequestDto>> violations = VALIDATOR.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "There should be validation violations for a blank email.");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")
                        &&
                        v.getMessage().equals("Email is mandatory")),
                "The email validation message should be 'Email is mandatory'.");
    }
}
