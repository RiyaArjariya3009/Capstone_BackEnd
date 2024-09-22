package com.ordermicroservice.OrderMicroservice.dtos;

import com.ordermicroservice.OrderMicroservice.entities.Cart;

import java.util.List;

public class OrderRequestDto {

    private int userId;

    private int deliveryAddressId;

    private int restaurantId;

    private List<Cart> cartItems;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(int deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Cart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Cart> cartItems) {
        this.cartItems = cartItems;
    }
}