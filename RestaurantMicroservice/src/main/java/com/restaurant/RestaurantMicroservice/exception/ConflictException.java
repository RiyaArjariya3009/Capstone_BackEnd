package com.restaurant.RestaurantMicroservice.exception;

/**
 * Exception thrown when a conflict occurs, such as when trying to
 * create a resource that already exists.
 */
public class ConflictException extends RuntimeException {

    /**
     * Constructs a new ConflictException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the conflict
     */
    public ConflictException(String message) {
        super(message);
    }
}
