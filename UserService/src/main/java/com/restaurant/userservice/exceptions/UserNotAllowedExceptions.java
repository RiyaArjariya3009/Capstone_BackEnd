package com.restaurant.userservice.exceptions;

/**
 * Exception thrown when a user is not allowed to perform a specific action.
 */
public class UserNotAllowedExceptions extends RuntimeException {

    /**
     * Constructs a new UserNotAllowedExceptions with the specified detail message.
     *
     * @param message the detail message
     */
    public UserNotAllowedExceptions(String message) {
        super(message);
    }
}
