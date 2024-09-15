package com.ordermicroservice.OrderMicroservice.feignclient;

import com.ordermicroservice.OrderMicroservice.dtos.RestaurantResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RestaurantMicroservice", url = "http://localhost:8082")
public interface RestaurantFeignClient {
    @GetMapping("/api/restaurants/getRestaurant/{id}")
    RestaurantResponseDto getRestaurantById(@PathVariable("id") int id);
}
