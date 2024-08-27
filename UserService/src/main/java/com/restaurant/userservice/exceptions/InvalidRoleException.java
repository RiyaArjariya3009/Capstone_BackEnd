package com.restaurant.userservice.exceptions;

public class InvalidRoleException extends RuntimeException{

    public InvalidRoleException(String message)
    {
        super(message);
    }
}
