package com.restaurant.userservice.dto.in;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Data Transfer Object for updating user information.
 * This class is used to encapsulate the data required to update a user's details.
 */
public class UserUpdateRequestDto {

    /**
     * The username of the user.
     * This field is mandatory.
     */
    @NotBlank(message = "Username is mandatory")
    private String username;

    /**
     * The email of the user.
     * This field is mandatory and must be a valid email address.
     */
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    /**
     * The password of the user.
     * This field is mandatory.
     */
    @NotBlank(message = "Password is mandatory")
    private String password;

    /**
     * The contact number of the user.
     * This field is mandatory.
     */
    @NotBlank(message = "Contact number is mandatory")
    private String contactNumber;

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public @NotBlank(message = "Username is mandatory") String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the new username of the user
     */
    public void setUsername(@NotBlank(message = "Username is mandatory") String username) {
        this.username = username;
    }

    /**
     * Gets the email of the user.
     *
     * @return the email of the user
     */
    public @NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the new email of the user
     */
    public void setEmail(@NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public @NotBlank(message = "Password is mandatory") String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password of the user
     */
    public void setPassword(@NotBlank(message = "Password is mandatory") String password) {
        this.password = password;
    }

    /**
     * Gets the contact number of the user.
     *
     * @return the contact number of the user
     */
    public @NotBlank(message = "Contact number is mandatory") String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets the contact number of the user.
     *
     * @param contactNumber the new contact number of the user
     */
    public void setContactNumber(@NotBlank(message = "Contact number is mandatory") String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
