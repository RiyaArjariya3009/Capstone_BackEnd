package com.restaurant.RestaurantMicroservice.service;

import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateRestaurantDetailRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.RestaurantDetailUpdateRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.RestaurantResponseDto;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service interface for managing restaurant operations.
 */
public interface RestaurantService {

    /**
     * Creates a new restaurant with the provided details and optional image.
     *
     * @param createRestaurantRequestDTO DTO containing the details for creating a restaurant.
     * @param multipartFile Optional image file for the restaurant.
     * @return A {@link CommonResponseDto} indicating the success of the creation operation.
     */
    @Transactional
    CommonResponseDto createRestaurant(CreateRestaurantDetailRequestDto createRestaurantRequestDTO,
                                       MultipartFile multipartFile);

    /**
     * Updates an existing restaurant with the provided details and optional image.
     *
     * @param id The ID of the restaurant to be updated.
     * @param restaurantUpdateRequestDTO DTO containing updated details for the restaurant.
     * @param multipartFile Optional image file for the restaurant.
     * @return A {@link CommonResponseDto} indicating the success of the update operation.
     */
    CommonResponseDto updateRestaurant(int id, RestaurantDetailUpdateRequestDto restaurantUpdateRequestDTO,
                                       MultipartFile multipartFile);

    /**
     * Deletes a restaurant by its ID.
     *
     * @param id The ID of the restaurant to be deleted.
     * @return A {@link CommonResponseDto} indicating the success of the deletion operation.
     */
    CommonResponseDto deleteRestaurant(int id);

    /**
     * Fetches the details of a restaurant by its ID.
     *
     * @param id The ID of the restaurant to be fetched.
     * @return A {@link RestaurantResponseDto} containing the details of the restaurant.
     */
    RestaurantResponseDto getRestaurantById(int id);

    /**
     * Updates the status (open/closed) of a restaurant.
     *
     * @param id The ID of the restaurant whose status is to be updated.
     * @return A {@link CommonResponseDto} indicating the success of the status update.
     */
    CommonResponseDto updateRestaurantStatus(int id);

    /**
     * Fetches all restaurants owned by a specific user.
     *
     * @param userId The ID of the user whose restaurants are to be fetched.
     * @return A list of {@link RestaurantResponseDto} containing the details of the restaurants.
     */
    List<RestaurantResponseDto> getRestaurantByUserId(int userId);

    /**
     * Fetches all restaurants in the system.
     *
     * @return A list of {@link RestaurantResponseDto} containing the details of all restaurants.
     */
    List<RestaurantResponseDto> getAllRestaurants();

}
