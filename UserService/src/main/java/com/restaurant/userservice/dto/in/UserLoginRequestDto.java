package com.restaurant.userservice.dto.in;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object for user login requests.
 * Contains the fields necessary for user authentication.
 */
public class UserLoginRequestDto {

    /**
     * Email of the user. This field is mandatory and must be a valid email address.
     */
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@nucleusteq\\.com$", message = "Email must be a valid mail address")
    private String email;

    /**
     * Password of the user. This field is mandatory and must be at least 8 characters long.
     */
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    /**
     * Default constructor.
     */
    public UserLoginRequestDto() {
        super();
    }

    /**
     * Parameterized constructor.
     *
     * @param email    the email of the user
     * @param password the password of the user
     */
    public UserLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
