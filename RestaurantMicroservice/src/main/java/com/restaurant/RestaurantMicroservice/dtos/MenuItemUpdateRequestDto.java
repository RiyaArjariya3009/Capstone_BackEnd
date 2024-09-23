package com.restaurant.RestaurantMicroservice.dtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for updating a menu item.
 * This DTO contains the necessary fields for updating
 * menu item information such as name, price, description, and category ID.
 */
public class MenuItemUpdateRequestDto {

    /**
     * Name of the menu item. Must not be blank and must contain only alphabetic characters and spaces.
     */
   // @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only alphabetic characters and spaces")
    private String foodName;

    /**
     * Price of the menu item. Must be greater than 0.
     */
    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    /**
     * Description of the menu item. Must not be blank and should be less than 255 characters.
     */
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    /**
     * Category ID of the menu item. Must be a positive integer.
     */
    //@Min(value = 1, message = "Category ID must be a positive integer")
     private int categoryId;

    /**
     * Gets the name of the menu item.
     * @return the name of the menu item
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * Sets the name of the menu item. Trims the input and replaces multiple spaces with a single space.
     * @param foodName the name of the menu item
     */
    public void setName(String foodName) {
        if (foodName != null) {
            this.foodName = foodName.trim().replaceAll("\\s+", " ").toUpperCase();
        } else {
            this.foodName = null;
        }
    }

    /**
     * Gets the price of the menu item.
     * @return the price of the menu item
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the menu item.
     * @param price the price of the menu item
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the category ID of the menu item.
     * @return the category ID of the menu item
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category ID of the menu item.
     * @param categoryId the category ID of the menu item
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the description of the menu item.
     * @return the description of the menu item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the menu item.
     * @param description the description of the menu item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Checks if two MenuItemUpdateRequestDto objects are equal based on name, price, description, and category ID.
     * @param o the other object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuItemUpdateRequestDto that = (MenuItemUpdateRequestDto) o;
        return categoryId == that.categoryId
                &&
                Objects.equals(foodName, that.foodName)
                &&
                Objects.equals(price, that.price)
                &&
                Objects.equals(description, that.description);
    }

    /**
     * Generates a hash code for the MenuItemUpdateRequestDto based on name, price, description, and category ID.
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(foodName, price, description, categoryId);
    }

    /**
     * Returns a string representation of the MenuItemUpdateRequestDto object.
     * @return a string representation of the MenuItemUpdateRequestDto
     */
    @Override
    public String toString() {
        return "MenuItemUpdateRequestDto{"
                + "name='" + foodName + '\''
                + ", price=" + price
                + ", description='" + description + '\''
                + ", categoryId=" + categoryId
                + '}';
    }


}
