package com.ordermicroservice.OrderMicroservice.service;

import com.ordermicroservice.OrderMicroservice.dtos.CommonResponseDto;
import com.ordermicroservice.OrderMicroservice.dtos.OrderRequestDto;
import com.ordermicroservice.OrderMicroservice.dtos.OrderResponseDto;

import java.util.List;


public interface OrderService {
       // CommonResponseDto placeOrder(OrderRequestDto placeOrderRequest);

    CommonResponseDto placeOrder(OrderRequestDto orderInDto);

   CommonResponseDto cancelOrder(int orderId);

    List<OrderResponseDto> getOrdersByUserId(int userId);

    List<OrderResponseDto> getOrdersByRestaurantId(int restaurantId);

    void markOrderAsCompleted(int orderId);

}


