package com.restaurant.RestaurantMicroservice.repository;

import com.restaurant.RestaurantMicroservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Category} entities.
 * This interface extends {@link JpaRepository} to provide basic CRUD operations
 * and custom queries related to categories in the system.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * Retrieves a list of categories associated with a specific restaurant.
     *
     * @param restaurantId the ID of the restaurant.
     * @return a list of {@link Category} entities linked to the specified restaurant.
     */
    List<Category> findByRestaurantId(int restaurantId);

    /**
     * Checks if a category with the specified name exists for a given restaurant.
     *
     * @param name the name of the category.
     * @param restaurantId the ID of the restaurant.
     * @return true if a category with the specified name exists for the given restaurant, false otherwise.
     */
    boolean existsByNameAndRestaurantId(String name, int restaurantId);
}
