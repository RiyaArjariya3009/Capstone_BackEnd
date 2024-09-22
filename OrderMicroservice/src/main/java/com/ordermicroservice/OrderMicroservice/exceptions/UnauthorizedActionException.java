package com.ordermicroservice.OrderMicroservice.exceptions;

public class UnauthorizedActionException extends RuntimeException{

    public UnauthorizedActionException(String message)
    {
        super(message);
    }
}
