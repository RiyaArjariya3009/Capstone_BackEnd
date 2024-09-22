package com.ordermicroservice.OrderMicroservice.entities;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordermicroservice.OrderMicroservice.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    private int deliveryAddressId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String cartItems;

    private LocalDateTime orderTime;

    private BigDecimal totalPrice;

    private int restaurantId;

    @Transient
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public List<Cart> getCartItemsAsList() throws JsonProcessingException {
        return objectMapper.readValue(cartItems, objectMapper.getTypeFactory().constructCollectionType(List.class, Cart.class));
    }

    public void setCartItemsFromList(List<Cart> cartItems) throws JsonProcessingException {
        this.cartItems = objectMapper.writeValueAsString(cartItems);
    }


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

    public String getCartItems() {
        return cartItems;
    }

    public void setCartItems(String cartItems) {
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

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}