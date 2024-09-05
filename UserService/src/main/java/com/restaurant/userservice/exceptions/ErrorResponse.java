package com.restaurant.userservice.exceptions;

import java.time.LocalDateTime;

/**
 * Represents an error response with details about the error.
 */
public class ErrorResponse {

    /** The HTTP status code associated with the error. */
    private int status;

    /** A message providing details about the error. */
    private String message;

    /** The timestamp when the error occurred. */
    private LocalDateTime timestamp;

    /**
     * Constructs a new ErrorResponse with the specified details.
     *
     * @param status the HTTP status code associated with the error
     * @param message a message providing details about the error
     * @param timestamp the timestamp when the error occurred
     */
    public ErrorResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    /**
     * Gets the HTTP status code associated with the error.
     *
     * @return the HTTP status code
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code associated with the error.
     *
     * @param status the HTTP status code
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the message providing details about the error.
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message providing details about the error.
     *
     * @param message the error message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the timestamp when the error occurred.
     *
     * @return the timestamp of the error
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when the error occurred.
     *
     * @param timestamp the timestamp of the error
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
