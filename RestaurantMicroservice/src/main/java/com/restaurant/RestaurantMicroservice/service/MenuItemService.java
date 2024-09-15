package com.restaurant.RestaurantMicroservice.service;

import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateMenuItemRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.MenuItemResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.MenuItemUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Service interface for managing menu items in the restaurant system.
 */
@Service
public interface MenuItemService {

    /**
     * Creates a new menu item with the provided details and image.
     *
     * @param createMenuItemRequestDTO DTO containing details for creating the menu item.
     * @param multipartFile Optional image file for the menu item.
     * @return A {@link CommonResponseDto} indicating the success of the creation operation.
     */
    CommonResponseDto createMenuItem(CreateMenuItemRequestDto createMenuItemRequestDTO, MultipartFile multipartFile);

    /**
     * Deletes a menu item by its ID.
     *
     * @param id The ID of the menu item to be deleted.
     * @return A {@link CommonResponseDto} indicating the success of the deletion operation.
     */
    CommonResponseDto deleteMenuItem(int id);

    /**
     * Fetches all menu items belonging to a specific category.
     *
     * @param categoryId The ID of the category for which to fetch menu items.
     * @return A list of {@link MenuItemResponseDto} containing the details of the menu items.
     */
    List<MenuItemResponseDto> getAllMenuItemsByCategoryId(int categoryId);

    /**
     * Fetches the details of a menu item by its ID.
     *
     * @param id The ID of the menu item to be fetched.
     * @return A {@link MenuItemResponseDto} containing the details of the menu item.
     */
    MenuItemResponseDto getMenuItemById(int id);

    /**
     * Fetches all menu items belonging to a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant for which to fetch menu items.
     * @return A list of {@link MenuItemResponseDto} containing the details of the menu items.
     */
    List<MenuItemResponseDto> getAllMenuItemsByRestaurantId(int restaurantId);

    /**
     * Updates the status (e.g., available or unavailable) of a menu item.
     *
     * @param id The ID of the menu item whose status is to be updated.
     * @return A {@link CommonResponseDto} indicating the success of the status update operation.
     */
    CommonResponseDto updateMenuItemStatus(int id);

    /**
     * Updates an existing menu item with the provided details and image.
     *
     * @param menuItemId The ID of the menu item to be updated.
     * @param menuItemUpdateRequestDTO DTO containing updated details for the menu item.
     * @param multipartFile Optional image file for the menu item.
     * @return A {@link CommonResponseDto} indicating the success of the update operation.
     */
    CommonResponseDto updateMenuItem(int menuItemId, MenuItemUpdateRequestDto menuItemUpdateRequestDTO,
                                     MultipartFile multipartFile);

}
