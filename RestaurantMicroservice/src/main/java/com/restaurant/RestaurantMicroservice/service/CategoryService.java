package com.restaurant.RestaurantMicroservice.service;

import com.restaurant.RestaurantMicroservice.dtos.CategoryResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateCategoryRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.UpdateCategoryRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing categories in the restaurant microservice.
 * This interface defines operations related to creating, updating, retrieving,
 * and deleting categories.
 */
@Service
public interface CategoryService {

    /**
     * Creates a new category based on the provided request data.
     *
     * @param createCategoryRequestDTO the data required to create a new category.
     * @return a {@link CommonResponseDto} indicating the result of the creation operation.
     */
    CommonResponseDto createCategory(CreateCategoryRequestDto createCategoryRequestDTO);

    /**
     * Updates an existing category identified by its ID with the provided data.
     *
     * @param id the ID of the category to update.
     * @param updateCategoryRequestDTO the data to update the category with.
     * @return a {@link CommonResponseDto} indicating the result of the update operation.
     */
    CommonResponseDto updateCategory(int id, UpdateCategoryRequestDto updateCategoryRequestDTO);

    /**
     * Retrieves a list of categories associated with a specific restaurant.
     *
     * @param restaurantId the ID of the restaurant whose categories are to be retrieved.
     * @return a list of {@link CategoryResponseDto} representing the categories of the specified restaurant.
     */
    List<CategoryResponseDto> getAllCategoriesByRestaurantId(int restaurantId);

    /**
     * Deletes a category identified by its ID.
     *
     * @param id the ID of the category to delete.
     * @return a {@link CommonResponseDto} indicating the result of the deletion operation.
     */
    CommonResponseDto deleteCategory(int id);

    /**
     * Retrieves the details of a category by its ID.
     *
     * @param id the ID of the category to retrieve.
     * @return a {@link CategoryResponseDto} representing the details of the specified category.
     */
    CategoryResponseDto getCategoriesById(int id);
}
