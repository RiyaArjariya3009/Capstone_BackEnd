package com.restaurant.RestaurantMicroservice.dtos;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for common response messages.
 */
public class CommonResponseDto {

    /**
     * The message associated with the response.
     */
    private String message;

    /**
     * Gets the message associated with the response.
     * @return the response message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message associated with the response.
     * @param message the response message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Compares this CommonResponseDto to another object for equality.
     * @param o the object to compare to.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommonResponseDto that = (CommonResponseDto) o;
        return Objects.equals(message, that.message);
    }

    /**
     * Returns a hash code value for the object.
     * @return the hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }

    /**
     * Returns a string representation of the CommonResponseDto.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "CommonResponseDto{"
                + "message='" + message + '\''
                + '}';
    }
}
