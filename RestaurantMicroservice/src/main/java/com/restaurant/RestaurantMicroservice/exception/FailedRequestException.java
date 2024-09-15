package com.restaurant.RestaurantMicroservice.exception;

/**
 * Exception thrown when a request fails due to a specific reason.
 * This could be used to indicate invalid requests or failed processing.
 */
public class FailedRequestException extends RuntimeException {

    /**
     * Constructs a new FailedRequestException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the failure.
     */
    public FailedRequestException(String message) {
        super(message);
    }
}
