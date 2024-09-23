package com.ordermicroservice.OrderMicroservice.dtos;

import javax.persistence.*;
import java.math.BigDecimal;

public class MenuItemResponseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;

    @Column(name = "category_id", nullable = false)
    private int categoryId;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "item_image", nullable = false)
    @Lob
    private byte[] imageData;

    //private Strin image;

    public MenuItemResponseDto() {
        super();
    }

    // Constructor with all fields


    public MenuItemResponseDto(int id, int restaurantId, int categoryId, String foodName, String description, Boolean isAvailable, BigDecimal price, byte[] imageData) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.categoryId = categoryId;
        this.foodName = foodName;
        this.description = description;
        this.isAvailable = isAvailable;
        this.price = price;
        this.imageData = imageData;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public Boolean isIsAvaiable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}


