package com.restaurant.userservice.exceptions;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
