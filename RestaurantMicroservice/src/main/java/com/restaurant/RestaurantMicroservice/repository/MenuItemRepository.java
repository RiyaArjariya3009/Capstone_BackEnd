package com.restaurant.RestaurantMicroservice.repository;

import com.restaurant.RestaurantMicroservice.entities.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link MenuItems} entities.
 * This interface extends {@link JpaRepository} to provide basic CRUD operations
 * and custom queries related to menu items in the system.
 */
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItems, Integer> {

    /**
     * Retrieves a list of menu items associated with a specific category.
     *
     * @param categoryId the ID of the category.
     * @return a list of {@link MenuItems} linked to the specified category.
     */
    List<MenuItems> findByCategoryId(int categoryId);

    /**
     * Retrieves a list of menu items associated with a specific restaurant.
     *
     * @param restaurantId the ID of the restaurant.
     * @return a list of {@link MenuItems} linked to the specified restaurant.
     */
    List<MenuItems> findByRestaurantId(int restaurantId);

    /**
     * Checks if a menu item with the specified food name exists for a given restaurant.
     *
     * @param foodName the name of the menu item (food).
     * @param restaurantId the ID of the restaurant.
     * @return true if a menu item with the specified name exists for the given restaurant, false otherwise.
     */
    boolean existsByFoodNameAndRestaurantId(String foodName, int restaurantId);
}
