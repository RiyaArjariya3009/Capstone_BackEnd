package com.ordermicroservice.OrderMicroservice.exceptions;

public class DifferentRestaurantException extends RuntimeException{

    public DifferentRestaurantException(String message)
    {
        super(message);
    }
}
