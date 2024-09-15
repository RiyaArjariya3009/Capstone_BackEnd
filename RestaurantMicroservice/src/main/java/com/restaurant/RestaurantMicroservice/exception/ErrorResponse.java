package com.restaurant.RestaurantMicroservice.exception;

/**
 * Represents an error response that contains the status code and message.
 */
public class ErrorResponse {

    /**
     * HTTP status code representing the error.
     */
    private int status;

    /**
     * Detailed message explaining the error.
     */
    private String message;

    /**
     * Constructs an ErrorResponse with the specified status and message.
     *
     * @param status  the HTTP status code of the error
     * @param message the detailed message explaining the error
     */
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Gets the HTTP status code of the error.
     *
     * @return the HTTP status code
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code of the error.
     *
     * @param status the HTTP status code
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the detailed message of the error.
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the detailed message of the error.
     *
     * @param message the error message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
