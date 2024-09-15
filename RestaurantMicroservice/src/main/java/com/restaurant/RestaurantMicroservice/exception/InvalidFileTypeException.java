package com.restaurant.RestaurantMicroservice.exception;

/**
 * Exception thrown when an invalid file type is encountered during processing.
 * This exception extends {@link RuntimeException}, and it is typically used
 * to signal that the provided file type is not supported or allowed.
 */
public class InvalidFileTypeException extends RuntimeException {

    /**
     * Constructs a new InvalidFileTypeException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public InvalidFileTypeException(String message) {
        super(message);
    }
}
