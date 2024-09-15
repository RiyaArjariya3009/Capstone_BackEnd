package com.restaurant.RestaurantMicroservice.exception;

/**
 * Exception thrown when an error occurs during the creation of a restaurant.
 * This exception extends {@link RuntimeException} and is typically used
 * to signal issues encountered while creating a new restaurant entity.
 */
public class RestaurantCreationException extends RuntimeException {

    /**
     * Constructs a new RestaurantCreationException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public RestaurantCreationException(String message) {
        super(message);
    }
}
