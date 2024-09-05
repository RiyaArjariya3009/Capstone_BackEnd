package com.restaurant.userservice.exceptions;

/**
 * Exception thrown when an address is not found.
 */
public class AddressNotFoundExceptions extends RuntimeException {

    /**
     * Constructs a new AddressNotFoundExceptions with the specified detail message.
     *
     * @param message the detail message
     */
    public AddressNotFoundExceptions(String message) {
        super(message);
    }
}
