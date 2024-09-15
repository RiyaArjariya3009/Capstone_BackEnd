package com.restaurant.RestaurantMicroservice.repository;

import com.restaurant.RestaurantMicroservice.entities.RestaurantDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link RestaurantDetail} entities.
 * This interface extends {@link JpaRepository} to provide basic CRUD operations
 * and custom queries related to restaurant details in the system.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantDetail, Integer> {

    /**
     * Retrieves a list of restaurant details associated with a specific owner.
     *
     * @param userId the ID of the owner.
     * @return a list of {@link RestaurantDetail} entities owned by the specified user.
     */
    List<RestaurantDetail> findByOwnerId(int userId);

    /**
     * Checks if a restaurant with the specified name exists, ignoring case.
     *
     * @param restaurantName the name of the restaurant.
     * @return true if a restaurant with the specified name exists, false otherwise.
     */
    boolean existsByRestaurantNameIgnoreCase(String restaurantName);
}
