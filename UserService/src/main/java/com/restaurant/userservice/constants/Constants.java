package com.restaurant.userservice.constants;

/**
 * This class contains constant values used across the User Service.
 * These constants represent common messages and default values
 * related to user operations such as registration, login, and wallet management.
 */
public class Constants {

    /**
     * Message indicating that the provided email is already in use.
     */
    public static final String EMAIL_ALREADY_IN_USE = "Email already in use.";

    /**
     * Message indicating that the provided number is already in use.
     */
    public static final String NUMBER_ALREADY_IN_USE = "Number already in use.";

    /**
     * Message indicating that registration was successful.
     */
    public static final String REGISTRATION_SUCCESSFUL = "Registration successful";

    /**
     * Message indicating that the provided email or password is invalid.
     */
    public static final String INVALID_CREDENTIALS = "Invalid email or password";

    /**
     * Message indicating that login was successful.
     */
    public static final String LOGIN_SUCCESSFUL = "Login successful";

    /**
     * Message indicating that a user with the specified ID was not found.
     */
    public static final String USER_NOT_FOUND = "User with this id was not found";

    /**
     * Default initial wallet balance for a new user.
     */
    public static final Double WALLET_BALANCE = 1000.00;

    /**
     * Default wallet balance when a user's wallet is not found.
     */
    public static final Double WALLET_BALANCE_NOT_FOUND = 0.0;

    /**
     * Message indicating that a user was successfully deleted.
     */
    public static final String USER_DELETED_SUCCESSFULLY = "User Deleted Successfully";

    /**
     * Message indicating that a user was successfully updated.
     */
    public static final String USER_UPDATED_SUCCESSFULLY = "User Updated Successfully";

    /**
     * Message indicating that an address was successfully deleted.
     */
    public static final String ADDRESS_DELETE_SUCCESSFULLY = "Address Deleted Successfully";

    /**
     * Message indicating that an address was successfully updated.
     */
    public static final String ADDRESS_UPDATE_SUCCESSFULLY = "Address Updated Successfully";

    /**
     * Message indicating that an address was not found.
     */
    public static final String ADDRESS_NOT_FOUND = "Address not found";

    /**
     * Message indicating that a user is not allowed to perform the requested action.
     */
    public static final String USER_NOT_ALLOWED = "User not allowed";
}
