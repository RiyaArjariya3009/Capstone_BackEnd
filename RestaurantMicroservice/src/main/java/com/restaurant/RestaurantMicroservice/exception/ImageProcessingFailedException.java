package com.restaurant.RestaurantMicroservice.exception;

public class ImageProcessingFailedException extends RuntimeException {
    /**
     * Constructs a new {@link ImageProcessingFailedException} with the specified detail message.
     *
     * @param message the detail message
     */
    public ImageProcessingFailedException(String message) {
        super(message);
    }

}
