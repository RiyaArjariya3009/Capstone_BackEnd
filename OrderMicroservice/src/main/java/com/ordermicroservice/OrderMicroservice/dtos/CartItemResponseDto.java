package com.ordermicroservice.OrderMicroservice.dtos;

import java.math.BigDecimal;

public class CartItemResponseDto {
    private int cartId;
    private int userId;
    private int restaurantId;
    private int menuItemId;
    private BigDecimal price;
    private Integer quantity;
    private String message;

    public CartItemResponseDto(int cartId, int userId, int restaurantId, int menuItemId, int quantity, String message) {
        this.cartId = cartId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.message = message;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CartItemResponseDto(int cartId, int userId, int restaurantId, int menuItemId, Integer quantity, String message) {
        this.cartId = cartId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.message = message;
    }

}

