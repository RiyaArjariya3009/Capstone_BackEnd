package com.ordermicroservice.OrderMicroservice.exceptions;

public class CartItemNotFoundException extends RuntimeException{

    public CartItemNotFoundException(String message)
    {
        super(message);
    }
}
