package com.restaurant.userservice.dto.in;

import com.restaurant.userservice.enums.RoleType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object for user registration requests.
 * Contains fields necessary for registering a new user.
 */
public class UserRegistrationRequestDto {

    /**
     * The username of the user. This field is mandatory and must be between 3 and 50 characters.
     */
    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    /**
     * The password of the user. This field is mandatory and must be at least 8 characters long.
     */
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    /**
     * The email address of the user. This field is mandatory, must be a valid email address,
     * and should have the domain 'nucleusteq.com'.
     */
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@nucleusteq\\.com$", message = "Email must be a valid mail address")
    private String email;

    /**
     * The role of the user. This field is mandatory and must be a valid role type.
     */
    @NotNull(message = "Role is mandatory")
    private RoleType role;

    /**
     * The contact number of the user. This field is mandatory, must be exactly 10 digits,
     * and should start with '986'.
     */
    @NotBlank(message = "Contact number is mandatory")
    @Size(min = 10, max = 10, message = "Contact number must be exactly 10 digits")
    @Pattern(regexp = "^[986].*", message = "Contact number must start with '9', '8', or '6'")
    private String contactNumber;


    /**
     * Default constructor.
     */
    public UserRegistrationRequestDto() {
        super();
    }

    /**
     * Parameterized constructor.
     *
     * @param username      the username of the user
     * @param password      the password of the user
     * @param email         the email address of the user
     * @param role          the role of the user
     * @param contactNumber the contact number of the user
     */
    public UserRegistrationRequestDto(String username, String password, String email, RoleType role, String contactNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.contactNumber = contactNumber;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
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

    /**
     * Gets the email address of the user.
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role of the user
     */
    public RoleType getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role of the user
     */
    public void setRole(RoleType role) {
        this.role = role;
    }

    /**
     * Gets the contact number of the user.
     *
     * @return the contact number of the user
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets the contact number of the user.
     *
     * @param contactNumber the contact number of the user
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
