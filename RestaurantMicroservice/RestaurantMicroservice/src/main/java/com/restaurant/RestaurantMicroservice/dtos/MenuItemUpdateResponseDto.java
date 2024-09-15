package com.restaurant.RestaurantMicroservice.dtos;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for updating a menu item response.
 * This class contains the details of a menu item after an update operation.
 */
public final class MenuItemUpdateResponseDto {

    /** The name of the menu item. */
    private String name;

    /** The price of the menu item. */
    private BigDecimal price;

    /** The description of the menu item. */
    private String description;

    /** The category name of the menu item. */
    private String categoryName;

    /** The message associated with the update response. */
    private String message;

    // private String imageUrl;

    /**
     * Gets the name of the menu item.
     *
     * @return the name of the menu item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the menu item.
     *
     * @param name the name of the menu item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the message associated with the update response.
     *
     * @return the update response message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message associated with the update response.
     *
     * @param message the update response message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the category name of the menu item.
     *
     * @return the category name of the menu item
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the category name of the menu item.
     *
     * @param categoryName the category name of the menu item
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Gets the price of the menu item.
     *
     * @return the price of the menu item
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the menu item.
     *
     * @param price the price of the menu item
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the description of the menu item.
     *
     * @return the description of the menu item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the menu item.
     *
     * @param description the description of the menu item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Compares this MenuItemUpdateResponseDto with another object for equality.
     * <p>
     * Subclasses should override this method to include additional fields in the equality check.
     * Ensure that the {@code equals} method satisfies the general contract of {@link Object#equals(Object)}.
     * </p>
     *
     * @param o the object to compare with
     * @return {@code true} if this object is equal to the other object; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuItemUpdateResponseDto that = (MenuItemUpdateResponseDto) o;
        return Objects.equals(name, that.name)
                && Objects.equals(price, that.price)
                && Objects.equals(description, that.description)
                && Objects.equals(categoryName, that.categoryName)
                && Objects.equals(message, that.message);
    }



    @Override
    public int hashCode() {
        return Objects.hash(name, price, description, categoryName, message);
    }

    @Override
    public String toString() {
        return "MenuItemUpdateResponseDto{"
                + "name='" + name + '\''
                + ", price=" + price
                + ", description='" + description + '\''
                + ", categoryName='" + categoryName + '\''
                + ", message='" + message + '\''
                + '}';
    }
}
