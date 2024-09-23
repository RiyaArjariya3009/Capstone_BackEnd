package com.ordermicroservice.OrderMicroservice.repositories;

import com.ordermicroservice.OrderMicroservice.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUserIdAndMenuItemIdAndRestaurantId(int userId, int menuItemId, int restaurantId);
    List<Cart> findByUserId(int userId);
    List<Cart> findByUserIdAndRestaurantId(int userId , int restaurantId);
}
