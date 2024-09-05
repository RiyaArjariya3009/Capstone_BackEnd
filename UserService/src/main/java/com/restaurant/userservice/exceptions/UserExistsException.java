package com.restaurant.userservice.exceptions;

/**
 * Exception thrown when a user already exists.
 */
public class UserExistsException extends RuntimeException {

    /**
     * Constructs a new UserExistsException with the specified detail message.
     *
     * @param message the detail message
     */
    public UserExistsException(String message) {
        super(message);
    }
}
