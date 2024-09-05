package com.restaurant.userservice.dto.out;

/**
 * Data Transfer Object for common response messages.
 * This DTO is used to encapsulate response messages that can be shared across different endpoints.
 */
public class CommonResponseDto {

    /** The response message. */
    private String message;

    /**
     * Constructs a new CommonResponseDto with the specified message.
     *
     *
     */
    public CommonResponseDto() {
        super();
    }

    /**
     * Constructs a new CommonResponseDto with the specified message.
     *
     * @param message the response message
     */
    public CommonResponseDto(String message) {
        this.message = message;
    }

    /**
     * Gets the response message.
     *
     * @return the response message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the response message.
     *
     * @param message the new response message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
