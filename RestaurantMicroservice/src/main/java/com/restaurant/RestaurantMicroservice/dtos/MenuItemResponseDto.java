package com.restaurant.RestaurantMicroservice.dtos;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

/**
 * A Data Transfer Object (DTO) for MenuItem Response.
 * This class is used to return the details of a menu item, including its name, price,
 * availability, and associated restaurant and category.
 */
public class MenuItemResponseDto {

    /**
     * The unique identifier for the menu item.
     */
    private int id;

    /**
     * The name of the restaurant associated with the menu item.
     */
    private String restaurantName;

    /**
     * The name of the category associated with the menu item.
     */
    private String categoryName;

    /**
     * The name of the food item.
     */
    private String foodName;

    /**
     * A brief description of the food item.
     */
    private String description;

    /**
     * Whether the menu item is available or not.
     */
    private Boolean isAvailable;

    /**
     * The price of the food item.
     */
    private BigDecimal price;

    /**
     * The image data associated with the food item, stored as a byte array.
     */
    private byte[] imageData;

    /**
     * Gets the unique identifier for the menu item.
     *
     * @return the ID of the menu item.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the menu item.
     *
     * @param id the ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the restaurant.
     *
     * @return the restaurant name.
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Sets the name of the restaurant.
     *
     * @param restaurantName the restaurant name to set.
     */
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    /**
     * Gets the category name of the menu item.
     *
     * @return the category name.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the category name of the menu item.
     *
     * @param categoryName the category name to set.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Gets the name of the food item.
     *
     * @return the food name.
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * Sets the name of the food item.
     *
     * @param foodName the food name to set.
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    /**
     * Gets the description of the food item.
     *
     * @return the description of the food item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the food item.
     *
     * @param description the description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the availability status of the menu item.
     *
     * @return the availability status.
     */
    public Boolean getIsAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability status of the menu item.
     *
     * @param isAvailable the availability status to set.
     */
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * Gets the price of the menu item.
     *
     * @return the price of the menu item.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the menu item.
     *
     * @param price the price to set.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the image data associated with the menu item.
     *
     * @return the image data as a byte array.
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * Sets the image data for the menu item.
     *
     * @param imageData the image data to set.
     */
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    /**
     * Compares this object with the specified object for equality.
     *
     * @param o the object to compare with.
     * @return {@code true} if the objects are equal, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuItemResponseDto that = (MenuItemResponseDto) o;
        return id == that.id
                && Objects.equals(restaurantName, that.restaurantName)
                && Objects.equals(categoryName, that.categoryName)
                && Objects.equals(foodName, that.foodName)
                && Objects.equals(description, that.description)
                && Objects.equals(isAvailable, that.isAvailable)
                && Objects.equals(price, that.price)
                && Arrays.equals(imageData, that.imageData);
    }

    /**
     * Returns the hash code for this object.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantName,
                categoryName, foodName, description, isAvailable, price, Arrays.hashCode(imageData));
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */

    @Override
    public String toString() {
        return "MenuItemResponseDto{"
                + "id=" + id
                + ", restaurantName='" + restaurantName + '\''
                + ", categoryName='" + categoryName + '\''
                + ", foodName='" + foodName + '\''
                + ", description='" + description + '\''
                + ", isAvailable=" + isAvailable
                + ", price=" + price
                + ", imageData=" + Arrays.toString(imageData)
                + '}';
    }


}
