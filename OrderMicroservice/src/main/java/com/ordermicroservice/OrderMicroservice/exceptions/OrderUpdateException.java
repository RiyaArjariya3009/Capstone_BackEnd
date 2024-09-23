package com.ordermicroservice.OrderMicroservice.exceptions;

public class OrderUpdateException extends RuntimeException{

    public OrderUpdateException(String message)
    {
        super(message);
    }
}
