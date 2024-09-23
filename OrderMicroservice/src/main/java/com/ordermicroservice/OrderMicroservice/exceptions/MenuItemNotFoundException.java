package com.ordermicroservice.OrderMicroservice.exceptions;

public class MenuItemNotFoundException extends RuntimeException{

    public MenuItemNotFoundException(String message)
    {
        super(message);
    }
}
