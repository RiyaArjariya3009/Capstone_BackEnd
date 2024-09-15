package com.restaurant.RestaurantMicroservice.dtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for creating a new menu item.
 */
public class CreateMenuItemRequestDto {

    /**
     * The ID of the restaurant to which the menu item belongs.
     * This field is mandatory and cannot be null.
     */
    @NotNull(message = "Restaurant id cannot be blank")
    private int restaurantId;

    /**
     * The ID of the category under which the menu item is listed.
     * This field is mandatory and cannot be null.
     */
    @NotNull(message = "Category id cannot be blank")
    private int categoryId;

    /**
     * The name of the food item.
     * This field is mandatory and has a maximum length of 100 characters.
     */
    @NotBlank(message = "Food name is required")
    @Size(max = 100, message = "Food name must be less than 100 characters")
    private String foodName;

    /**
     * The description of the food item.
     * This field is mandatory and has a maximum length of 255 characters.
     */
    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    /**
     * The price of the food item.
     * This field is mandatory and must be greater than zero.
     */
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    /**
     * Gets the restaurant ID.
     * @return the restaurant ID.
     */
    public int getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets the restaurant ID.
     * @param restaurantId the restaurant ID to set.
     */
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Gets the category ID.
     * @return the category ID.
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category ID.
     * @param categoryId the category ID to set.
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the name of the food item.
     * @return the food name.
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * Sets the name of the food item.
     * The name is trimmed and extra spaces are replaced with a single space.
     * @param foodName the food name to set.
     */
    public void setFoodName(String foodName) {
        if (foodName != null) {
            this.foodName = foodName.trim().replaceAll("\\s+", " ");
        } else {
            this.foodName = null;
        }
    }

    /**
     * Gets the description of the food item.
     * @return the food description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the food item.
     * @param description the description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price of the food item.
     * @return the food price.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the food item.
     * @param price the price to set.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Compares this CreateMenuItemRequestDto to another object for equality.
     * @param o the object to compare to.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateMenuItemRequestDto that = (CreateMenuItemRequestDto) o;
        return restaurantId == that.restaurantId
                && categoryId == that.categoryId
                && Objects.equals(foodName, that.foodName)
                && Objects.equals(description, that.description)
                && Objects.equals(price, that.price);
    }

    /**
     * Returns a hash code value for the object.
     * @return the hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, categoryId, foodName, description, price);
    }

    /**
     * Returns a string representation of the CreateMenuItemRequestDto.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "CreateMenuItemRequestDto{"
                + "restaurantId=" + restaurantId
                + ", categoryId=" + categoryId
                + ", foodName='" + foodName + '\''
                + ", description='" + description + '\''
                + ", price=" + price
                + '}';
    }
}
