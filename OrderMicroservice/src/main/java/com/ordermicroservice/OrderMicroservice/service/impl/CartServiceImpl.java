package com.ordermicroservice.OrderMicroservice.service.impl;

import com.ordermicroservice.OrderMicroservice.dtos.*;
import com.ordermicroservice.OrderMicroservice.entities.Cart;
import com.ordermicroservice.OrderMicroservice.exceptions.CartItemNotFoundException;
import com.ordermicroservice.OrderMicroservice.exceptions.DifferentRestaurantException;
import com.ordermicroservice.OrderMicroservice.exceptions.UnauthorizedActionException;
import com.ordermicroservice.OrderMicroservice.feignclient.UserFeignClient;
import com.ordermicroservice.OrderMicroservice.repositories.CartRepository;
import com.ordermicroservice.OrderMicroservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserFeignClient userFeignClient;
    @Override
    public CommonResponseDto addItemToCart(CartItemRequestDto cartItemRequestDto) {
        // Validate User
        UserResponseDto user = userFeignClient.getUserById(cartItemRequestDto.getUserId());
        if (user == null || !user.getRole().equals("CUSTOMER")) {
            throw new UnauthorizedActionException("Only customers can add items to the cart.");
        }
        List<Cart> existingCartItems = cartRepository.findByUserId(cartItemRequestDto.getUserId());

       /* if (!existingCartItems.isEmpty()) {
            Integer existingRestaurantId = existingCartItems.get(0).getRestaurantId();
            if (!existingRestaurantId.equals(cartItemRequestDto.getRestaurantId())) {
                throw new DifferentRestaurantException("You can only add items from one restaurant at a time. Please clear your cart before adding items from a different restaurant.");
            }
        }*/

        Optional<Cart> existingCartItem = cartRepository.findByUserIdAndMenuItemIdAndRestaurantId(
                cartItemRequestDto.getUserId(), cartItemRequestDto.getMenuItemId(), cartItemRequestDto.getRestaurantId());

        if (existingCartItem.isPresent()) {
            return updateCartItemQuantity(existingCartItem.get().getCartId(), 1);
        } else {

            return addNewCartItem(cartItemRequestDto);

        }

    }
    private CommonResponseDto addNewCartItem(CartItemRequestDto cartItemRequestDto) {
        Cart newCart = new Cart();
        newCart.setUserId(cartItemRequestDto.getUserId());
        newCart.setMenuItemId(cartItemRequestDto.getMenuItemId());
        newCart.setRestaurantId(cartItemRequestDto.getRestaurantId());
        newCart.setQuantity(1);
        newCart.setPrice(cartItemRequestDto.getPrice());
        cartRepository.save(newCart);
        return new CommonResponseDto("Cart Added Successfully");

    }
    @Override
    public CommonResponseDto updateCartItemQuantity(int cartId, int quantityChange) {
        Cart cart = getCartById(cartId);

        BigDecimal unitPrice = cart.getPrice().divide(BigDecimal.valueOf(cart.getQuantity()), BigDecimal.ROUND_HALF_EVEN);
        int newQuantity = Math.max(0, cart.getQuantity() + quantityChange);
        if (newQuantity == 0) {
            cartRepository.deleteById(cartId);
            return new CommonResponseDto("Item Removed Successfully");
        }
        BigDecimal newPrice = unitPrice.multiply(BigDecimal.valueOf(newQuantity));
        cart.setQuantity(newQuantity);
        cart.setPrice(newPrice);
        cartRepository.save(cart);
        return new CommonResponseDto("Cart Updated Successfully");  }

    @Override
    public CommonResponseDto removeItemFromCart(int cartId) {
        Optional<Cart> cartItem = cartRepository.findById(cartId);
        if (!cartItem.isPresent()) {
            throw new CartItemNotFoundException("Cart item not found.");
        }

        cartRepository.delete(cartItem.get());

        return new CommonResponseDto("Item removed from cart successfully");
    }
    @Override
    public CommonResponseDto clearCartAfterOrderPlaced(int userId, int restaurantId) {
        List<Cart> cartItems = cartRepository.findByUserIdAndRestaurantId(userId, restaurantId);
        if (!cartItems.isEmpty()) {
            cartRepository.deleteAll(cartItems);
            return new CommonResponseDto("Cart Deleted Successfully");
        } else {
            return new CommonResponseDto("Cart Already Empty");
        }
    }


    @Override
    public List<Cart> getCartItemsByUserIdAndRestaurantId(int userId, int restaurantId) {
        return cartRepository.findByUserIdAndRestaurantId(userId, restaurantId);
    }
    @Override
    public List<Cart> getCartByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }
    @Override
    public Cart getCartById(int cartId){
        return cartRepository.findById(cartId)
                .orElseThrow(() -> {
                    return new CartItemNotFoundException("Cart Item Not Found");
                });
    }
}
