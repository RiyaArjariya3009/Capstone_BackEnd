package com.restaurant.RestaurantMicroservice.exception;

/**
 * Exception thrown when a requested resource is not found.
 * This exception extends {@link RuntimeException} and is typically used
 * to indicate that a specific resource (such as a restaurant or item) could not be located.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
