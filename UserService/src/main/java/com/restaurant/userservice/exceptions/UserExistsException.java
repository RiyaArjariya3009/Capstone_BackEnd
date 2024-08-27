package com.restaurant.userservice.exceptions;

public class UserExistsException extends RuntimeException{

    public UserExistsException(String message)
    {
        super(message);
    }
}
