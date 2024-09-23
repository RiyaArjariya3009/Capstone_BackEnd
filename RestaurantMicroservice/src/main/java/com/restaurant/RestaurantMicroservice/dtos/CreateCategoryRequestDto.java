package com.restaurant.RestaurantMicroservice.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for creating a new category.
 */
public class CreateCategoryRequestDto {

    /**
     * The name of the category.
     * This field is mandatory and cannot be blank.
     */
    @NotBlank(message = "Category name is required")
    private String name;

    /**
     * The ID of the restaurant to which the category belongs.
     * This field is mandatory and cannot be null.
     */
    @NotNull(message = "Restaurant ID is required")
    private int restaurantId;

    /**
     * Gets the name of the category.
     * @return the name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     * The name is trimmed and extra spaces are replaced with a single space.
     * If the provided name is null, it is set to null.
     * @param name the name of the category to set.
     */
    public void setName(String name) {
        if (name != null) {
            this.name = name.trim().replaceAll("\\s+", " ").toUpperCase();
        } else {
            this.name = null;
        }
    }

    /**
     * Gets the ID of the restaurant.
     * @return the restaurant ID.
     */
    public int getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets the ID of the restaurant.
     * @param restaurantId the restaurant ID to set.
     */
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Compares this CreateCategoryRequestDto to another object for equality.
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
        CreateCategoryRequestDto that = (CreateCategoryRequestDto) o;
        return restaurantId == that.restaurantId && Objects.equals(name, that.name);
    }

    /**
     * Returns a hash code value for the object.
     * @return the hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, restaurantId);
    }

    /**
     * Returns a string representation of the CreateCategoryRequestDto.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "CreateCategoryRequestDto{"
                + "name='" + name + '\''
                + ", restaurantId=" + restaurantId
                + '}';
    }
}
