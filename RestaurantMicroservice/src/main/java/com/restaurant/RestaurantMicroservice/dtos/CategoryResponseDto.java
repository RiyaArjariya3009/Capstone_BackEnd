package com.restaurant.RestaurantMicroservice.dtos;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) representing a category response.
 */
public class CategoryResponseDto {

    /**
     * The unique identifier of the category.
     */
    private int id;

    /**
     * The name of the category.
     */
    private String name;

    /**
     * The unique identifier of the restaurant to which the category belongs.
     */
    private int restaurantId;

    /**
     * Gets the unique identifier of the category.
     * @return the category ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the category.
     * @param id the category ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the category.
     * @return the category name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     * @param name the category name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the unique identifier of the restaurant to which the category belongs.
     * @return the restaurant ID.
     */
    public int getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets the unique identifier of the restaurant to which the category belongs.
     * @param restaurantId the restaurant ID to set.
     */
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Compares this CategoryResponseDto to another object for equality.
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
        CategoryResponseDto that = (CategoryResponseDto) o;
        return id == that.id
                && restaurantId == that.restaurantId
                && Objects.equals(name, that.name);
    }

    /**
     * Returns a hash code value for the object.
     * @return the hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, restaurantId);
    }

    /**
     * Returns a string representation of the CategoryResponseDto.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "CategoryResponseDto{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", restaurantId=" + restaurantId
                + '}';
    }
}
