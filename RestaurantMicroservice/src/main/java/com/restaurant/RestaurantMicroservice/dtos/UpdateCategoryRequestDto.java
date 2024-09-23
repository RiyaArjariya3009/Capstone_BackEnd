package com.restaurant.RestaurantMicroservice.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * DTO for updating a category.
 */
public class UpdateCategoryRequestDto {

    /**
     * The name of the category.
     * Must be between 3 and 100 characters long, and contain only alphabetic characters and spaces.
     */
    @NotBlank(message = "Category name cannot be blank")
    @Size(min = 3, max = 100, message = "Category name must be between 3 and 100 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Category name must contain only alphabetic characters and spaces")
    private String name;

    /**
     * Gets the name of the category.
     *
     * @return the name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     * Trims and replaces multiple spaces with a single space.
     *
     * @param name the name of the category.
     */
    public void setName(String name) {
        if (name != null) {
            this.name = name.trim().replaceAll("\\s+", " ").toUpperCase();
        } else {
            this.name = null;
        }
    }

    /**
     * Compares this object with the specified object for equality.
     *
     * @param o the object to compare with.
     * @return {@code true} if this object is the same as the specified object;
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateCategoryRequestDto that = (UpdateCategoryRequestDto) o;
        return Objects.equals(name, that.name);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "UpdateCategoryRequestDto{"
                + "name='" + name + '\''
                + '}';
    }
}
