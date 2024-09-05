package com.restaurant.userservice.dtos.in;
import com.restaurant.userservice.dto.in.UserRegistrationRequestDto;
import com.restaurant.userservice.enums.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link com.restaurant.userservice.dto.in.UserRegistrationRequestDto} class.
 * These tests validate the behavior of the DTO, including constraint validations
 * for its fields.
 */
@ExtendWith(MockitoExtension.class)
public class UserRegistrationRequestDtoTest {

    /** The validator instance used for validating DTOs. */
    private static Validator validator;

    /**
     * Sets up the validator instance before each test.
     * This method initializes the validator factory and obtains a validator.
     */
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Tests a valid {@link com.restaurant.userservice.dto.in.UserRegistrationRequestDto} to ensure that
     * no validation violations are present.
     * This test creates a DTO with all valid fields and checks that
     * there are no validation errors.
     */
    @Test
    public void testValidUserRegistrationRequest() {
        UserRegistrationRequestDto dto = new UserRegistrationRequestDto(
                "JohnDoe",
                "password123",
                "john.doe@nucleusteq.com",
                RoleType.CUSTOMER,
                "9861234567"
        );

        Set<javax.validation.ConstraintViolation<UserRegistrationRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "There should be no validation violations");
    }

    /**
     * Tests an invalid email address in the {@link UserRegistrationRequestDto}
     * to ensure that the correct validation message is returned.
     * This test sets an invalid email domain and checks for the appropriate
     * validation message.
     */
    @Test
    public void testInvalidEmail() {
        UserRegistrationRequestDto dto = new UserRegistrationRequestDto(
                "JohnDoe",
                "password123",
                "john.doe@gmail.com",  // Invalid email domain
                RoleType.CUSTOMER,
                "9861234567"
        );

        Set<javax.validation.ConstraintViolation<UserRegistrationRequestDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size(), "There should be one validation violation");
        assertEquals("Email must be a valid mail address", violations.iterator().next().getMessage());
    }

    /**
     * Tests an invalid password in the {@link UserRegistrationRequestDto}
     * to ensure that the correct validation message is returned.
     * This test sets a password that is shorter than required and checks
     * for the appropriate validation message.
     */
    @Test
    public void testInvalidPassword() {
        UserRegistrationRequestDto dto = new UserRegistrationRequestDto(
                "JohnDoe",
                "short",  // Invalid password
                "john.doe@nucleusteq.com",
                RoleType.CUSTOMER,
                "9861234567"
        );

        Set<javax.validation.ConstraintViolation<UserRegistrationRequestDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size(), "There should be one validation violation");
        assertEquals("Password must be at least 8 characters long", violations.iterator().next().getMessage());
    }

    /**
     * Tests an invalid contact number in the {@link UserRegistrationRequestDto}
     * to ensure that the correct validation message is returned.
     * This test sets a contact number that does not start with '986' and checks
     * for the appropriate validation message.
     */
    @Test
    public void testInvalidContactNumber() {
        UserRegistrationRequestDto dto = new UserRegistrationRequestDto(
                "JohnDoe",
                "password123",
                "john.doe@nucleusteq.com",
                RoleType.CUSTOMER,
                "7851234567"  // Invalid contact number (does not start with 9, 8, or 6)
        );

        Set<javax.validation.ConstraintViolation<UserRegistrationRequestDto>> violations = validator.validate(dto);
        assertEquals(1, violations.size(), "There should be one validation violation");
        assertEquals("Contact number must start with '9', '8', or '6'", violations.iterator().next().getMessage());
    }


}



