package com.restaurant.RestaurantMicroservice.exception;

/**
 * Exception thrown when an unauthorized access attempt is made.
 * This exception extends {@link RuntimeException} and is used to indicate
 * that the current request or action is not authorized.
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * Constructs a new UnauthorizedException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}
