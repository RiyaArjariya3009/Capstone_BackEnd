package com.ordermicroservice.OrderMicroservice.exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message)
    {
        super(message);
    }
}
