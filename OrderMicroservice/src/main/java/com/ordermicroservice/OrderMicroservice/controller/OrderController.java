package com.ordermicroservice.OrderMicroservice.controller;

import com.ordermicroservice.OrderMicroservice.dtos.CommonResponseDto;
import com.ordermicroservice.OrderMicroservice.dtos.OrderRequestDto;
import com.ordermicroservice.OrderMicroservice.dtos.OrderResponseDto;
import com.ordermicroservice.OrderMicroservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<CommonResponseDto> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        CommonResponseDto response = orderService.placeOrder(orderRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<CommonResponseDto> cancelOrder(@PathVariable int orderId) {
        CommonResponseDto response = orderService.cancelOrder(orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


   /* @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<CommonResponseDto> cancelOrder(@PathVariable int orderId) {
        CommonResponseDto response = orderService.cancelOrder(orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUserId(@PathVariable int userId) {
        List<OrderResponseDto> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByRestaurantId(@PathVariable int restaurantId) {
        List<OrderResponseDto> orders = orderService.getOrdersByRestaurantId(restaurantId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/complete/{orderId}")
    public ResponseEntity<Void> markOrderAsCompleted(@PathVariable int orderId) {
        orderService.markOrderAsCompleted(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
