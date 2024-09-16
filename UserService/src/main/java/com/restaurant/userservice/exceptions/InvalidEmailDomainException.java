package com.restaurant.userservice.exceptions;

/**
 * Exception thrown when an invalid email domain is encountered.
 */
public class InvalidEmailDomainException extends RuntimeException {

    /**
     * Constructs a new InvalidEmailDomainException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidEmailDomainException(String message) {
        super(message);
    }
}
