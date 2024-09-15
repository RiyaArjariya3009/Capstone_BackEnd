package com.ordermicroservice.OrderMicroservice.dtos;

import com.ordermicroservice.OrderMicroservice.entities.Cart;
import com.ordermicroservice.OrderMicroservice.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class OrderResponseDto {

        private int id;

        private int userId;

        private int deliveryAddressId;

        private OrderStatus orderStatus;

        private List<Cart> cartItems;

        private LocalDateTime orderTime;

        private BigDecimal totalPrice;

        private int restaurantId;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

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

        public OrderStatus getOrderStatus() {
                return orderStatus;
        }

        public void setOrderStatus(OrderStatus orderStatus) {
                this.orderStatus = orderStatus;
        }

        public List<Cart> getCartItems() {
                return cartItems;
        }

        public void setCartItems(List<Cart> cartItems) {
                this.cartItems = cartItems;
        }

        public LocalDateTime getOrderTime() {
                return orderTime;
        }

        public void setOrderTime(LocalDateTime orderTime) {
                this.orderTime = orderTime;
        }

        public BigDecimal getTotalPrice() {
                return totalPrice;
        }

        public void setTotalPrice(BigDecimal totalPrice) {
                this.totalPrice = totalPrice;
        }

        public int getRestaurantId() {
                return restaurantId;
        }

        public void setRestaurantId(int restaurantId) {
                this.restaurantId = restaurantId;
        }
}





