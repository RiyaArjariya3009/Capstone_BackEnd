package com.restaurant.RestaurantMicroservice.FeignClient;

import com.restaurant.RestaurantMicroservice.dtos.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client interface for communicating with the User Service.
 * This client is used to fetch user information by user ID via REST API calls to the User Service.
 * It uses Feign to handle the HTTP communication with the external service.
 */
@FeignClient(name = "UserService", url = "http://localhost:8081")
public interface UserFeignClient {

    /**
     * Fetches user information from the User Service by user ID.
     *
     * @param id the ID of the user to retrieve.
     * @return a {@link UserResponseDto} containing the user details.
     */
    @GetMapping("/api/auth/getUserBy/{id}")
    UserResponseDto getUserById(@PathVariable("id") int id);
}
