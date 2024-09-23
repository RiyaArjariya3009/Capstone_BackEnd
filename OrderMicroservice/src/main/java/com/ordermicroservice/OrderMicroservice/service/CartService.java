package com.ordermicroservice.OrderMicroservice.service;

import com.ordermicroservice.OrderMicroservice.dtos.CartItemRequestDto;
import com.ordermicroservice.OrderMicroservice.dtos.CommonResponseDto;
import com.ordermicroservice.OrderMicroservice.entities.Cart;

import java.util.List;

public interface CartService {
    CommonResponseDto addItemToCart(CartItemRequestDto cartItemRequestDto);
    CommonResponseDto updateCartItemQuantity(int cartId, int quantityChange);
    CommonResponseDto removeItemFromCart(int cartId);
    CommonResponseDto clearCartAfterOrderPlaced(int userId, int restaurantId);
    List<Cart> getCartByUserId(int userId);
    List<Cart> getCartItemsByUserIdAndRestaurantId(int userId, int restaurantId);
    Cart getCartById(int userId);
}

