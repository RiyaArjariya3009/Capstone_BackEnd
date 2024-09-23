package com.ordermicroservice.OrderMicroservice.DtoConversion;

import com.ordermicroservice.OrderMicroservice.dtos.CartItemRequestDto;
import com.ordermicroservice.OrderMicroservice.dtos.OrderRequestDto;
import com.ordermicroservice.OrderMicroservice.dtos.OrderResponseDto;
import com.ordermicroservice.OrderMicroservice.entities.Cart;
import com.ordermicroservice.OrderMicroservice.entities.Order;

import java.util.Collections;

public final class DtoConversion {

        private DtoConversion(){
            throw new UnsupportedOperationException("Utility class");
        }

        public static Cart convertCartInDtoToCart(CartItemRequestDto cartRequestDto){
            Cart cartItem = new Cart();
            cartItem.setMenuItemId(cartRequestDto.getMenuItemId());
            cartItem.setQuantity(cartRequestDto.getQuantity());
            cartItem.setPrice(cartRequestDto.getPrice());
            cartItem.setRestaurantId(cartRequestDto.getRestaurantId());
            cartItem.setUserId(cartRequestDto.getUserId());
            return cartItem;
        }

        public static Order convertOrderInDtoToOrder(OrderRequestDto orderRequestDto){
            Order order = new Order();
            order.setUserId(orderRequestDto.getUserId());
            order.setDeliveryAddressId(orderRequestDto.getDeliveryAddressId());
            order.setRestaurantId(orderRequestDto.getRestaurantId());
            return order;
        }

        public static OrderResponseDto convertOrderToOrderOutDto(Order order){
            OrderResponseDto dto= new OrderResponseDto();
            dto.setId(order.getId());
            dto.setDeliveryAddressId(order.getDeliveryAddressId());
            dto.setUserId(order.getUserId());
            dto.setRestaurantId(order.getRestaurantId());
            dto.setTotalPrice(order.getTotalPrice());
            dto.setOrderStatus(order.getOrderStatus());
            dto.setOrderTime(order.getOrderTime());
            try {
                dto.setCartItems(order.getCartItemsAsList());
            } catch (Exception e) {
                dto.setCartItems(Collections.emptyList());
            }    return dto;
        }

}
