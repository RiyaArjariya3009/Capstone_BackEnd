package com.ordermicroservice.OrderMicroservice.feignclient;

import com.ordermicroservice.OrderMicroservice.dtos.AddressResponseDto;
import com.ordermicroservice.OrderMicroservice.dtos.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "AddressServiceFeignClient", url = "http://localhost:8081")
public interface AddressFeignClient {

    @GetMapping("api/address/getAddressesByUserId/{userId}")
    List<AddressResponseDto> getAddressesByUserId(@PathVariable("userId") Integer userId);

    @GetMapping("api/address/getAddressById/{id}")
    AddressResponseDto getAddressById(@PathVariable("id") int id);
}

