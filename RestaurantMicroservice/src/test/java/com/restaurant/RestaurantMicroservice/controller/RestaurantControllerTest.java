package com.restaurant.RestaurantMicroservice.controller;

import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateRestaurantDetailRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.RestaurantDetailUpdateRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.RestaurantResponseDto;
import com.restaurant.RestaurantMicroservice.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestaurantControllerTest {

    @InjectMocks
    private RestaurantController restaurantController;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private MultipartFile multipartFile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addRestaurant_shouldCreateRestaurant() {
        CreateRestaurantDetailRequestDto requestDto = new CreateRestaurantDetailRequestDto();
        requestDto.setRestaurantName("Test Restaurant");
        CommonResponseDto responseDto = new CommonResponseDto("Restaurant created successfully");

        when(restaurantService.createRestaurant(any(), any())).thenReturn(responseDto);

        ResponseEntity<CommonResponseDto> response = restaurantController.addRestaurant(requestDto, multipartFile);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(restaurantService, times(1)).createRestaurant(any(), any());
    }

    @Test
    void updateRestaurant_shouldUpdateRestaurant() {
        int restaurantId = 1;
        RestaurantDetailUpdateRequestDto updateRequestDto = new RestaurantDetailUpdateRequestDto();
        CommonResponseDto responseDto = new CommonResponseDto("Restaurant updated successfully");

        when(restaurantService.updateRestaurant(anyInt(), any(), any())).thenReturn(responseDto);

        ResponseEntity<CommonResponseDto> response = restaurantController.updateRestaurant(restaurantId, updateRequestDto, multipartFile);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(restaurantService, times(1)).updateRestaurant(anyInt(), any(), any());
    }

    @Test
    void deleteRestaurant_shouldDeleteRestaurant() {
        int restaurantId = 1;
        CommonResponseDto responseDto = new CommonResponseDto("Restaurant deleted successfully");

        when(restaurantService.deleteRestaurant(restaurantId)).thenReturn(responseDto);

        ResponseEntity<CommonResponseDto> response = restaurantController.deleteRestaurant(restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(restaurantService, times(1)).deleteRestaurant(restaurantId);
    }

    @Test
    void getRestaurantById_shouldReturnRestaurant() {
        int restaurantId = 1;
        RestaurantResponseDto responseDto = new RestaurantResponseDto();
        responseDto.setRestaurantName("Test Restaurant");

        when(restaurantService.getRestaurantById(restaurantId)).thenReturn(responseDto);

        ResponseEntity<RestaurantResponseDto> response = restaurantController.getRestaurantById(restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(restaurantService, times(1)).getRestaurantById(restaurantId);
    }

    @Test
    void getAllRestaurants_shouldReturnAllRestaurants() {
        List<RestaurantResponseDto> responseDtos = new ArrayList<>();
        RestaurantResponseDto restaurant = new RestaurantResponseDto();
        restaurant.setRestaurantName("Test Restaurant");
        responseDtos.add(restaurant);

        when(restaurantService.getAllRestaurants()).thenReturn(responseDtos);

        ResponseEntity<List<RestaurantResponseDto>> response = restaurantController.getAllRestaurants();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDtos, response.getBody());
        verify(restaurantService, times(1)).getAllRestaurants();
    }

    @Test
    void getRestaurantByUserId_shouldReturnRestaurants() {
        int userId = 1;
        List<RestaurantResponseDto> responseDtos = new ArrayList<>();
        RestaurantResponseDto restaurant = new RestaurantResponseDto();
        restaurant.setRestaurantName("Test Restaurant");
        responseDtos.add(restaurant);

        when(restaurantService.getRestaurantByUserId(userId)).thenReturn(responseDtos);

        ResponseEntity<List<RestaurantResponseDto>> response = restaurantController.getRestaurantByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDtos, response.getBody());
        verify(restaurantService, times(1)).getRestaurantByUserId(userId);
    }
}
