package com.ordermicroservice.OrderMicroservice.feignclient;

import com.ordermicroservice.OrderMicroservice.dtos.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "UserService", url = "http://localhost:8081")
public interface UserFeignClient {

    @GetMapping("/api/auth/getUserBy/{id}")
    UserResponseDto getUserById(@PathVariable("id") int id);

    @PutMapping("/api/auth/wallet/{id}")
    void updateWalletBalance(@PathVariable("id") int id, @RequestBody Double amount);
}

