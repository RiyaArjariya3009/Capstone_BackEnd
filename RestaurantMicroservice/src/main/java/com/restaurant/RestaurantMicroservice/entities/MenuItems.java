package com.restaurant.RestaurantMicroservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a menu item in a restaurant.
 */
@Entity
@Table(name = "menu_items")
public class MenuItems {

    /**
     * The unique identifier for the menu item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The ID of the restaurant to which this menu item belongs.
     */
    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;

    /**
     * The ID of the category to which this menu item belongs.
     */
    @Column(name = "category_id", nullable = false)
    private int categoryId;

    /**
     * The name of the menu item.
     */
    @Column(name = "food_name", nullable = false)
    private String foodName;

    /**
     * A description of the menu item.
     */
    @Column(name = "description")
    private String description;

    /**
     * Indicates whether the menu item is available.
     */
    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    /**
     * The price of the menu item.
     */
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    /**
     * The image data of the menu item.
     */
    @Column(name = "item_image", nullable = false)
    @Lob
    private byte[] imageData;

    /**
     * Default constructor.
     */
    public MenuItems() {
        super();
    }

    /**
     * Parameterized constructor.
     *
     * @param id             the unique identifier for the menu item
     * @param restaurantId   the ID of the restaurant to which this menu item belongs
     * @param categoryId     the ID of the category to which this menu item belongs
     * @param foodName       the name of the menu item
     * @param description    a description of the menu item
     * @param isAvailable    indicates whether the menu item is available
     * @param price          the price of the menu item
     * @param imageData      the image data of the menu item
     */
    public MenuItems(int id, int restaurantId, int categoryId, String foodName, String description, Boolean isAvailable,
                     BigDecimal price, byte[] imageData) {
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

    /**
     * Gets the unique identifier for the menu item.
     *
     * @return the ID of the menu item
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the menu item.
     *
     * @param id the ID of the menu item
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the restaurant to which this menu item belongs.
     *
     * @return the restaurant ID
     */
    public int getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets the ID of the restaurant to which this menu item belongs.
     *
     * @param restaurantId the restaurant ID
     */
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Gets the ID of the category to which this menu item belongs.
     *
     * @return the category ID
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the ID of the category to which this menu item belongs.
     *
     * @param categoryId the category ID
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the name of the menu item.
     *
     * @return the name of the menu item
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * Sets the name of the menu item.
     *
     * @param foodName the name of the menu item
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName;
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
     * Gets whether the menu item is available.
     *
     * @return true if the menu item is available, false otherwise
     */
    public Boolean getAvailable() {
        return isAvailable;
    }

    /**
     * Sets whether the menu item is available.
     *
     * @param available true if the menu item is available, false otherwise
     */
    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    /**
     * Gets the image data of the menu item.
     *
     * @return the image data of the menu item
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * Sets the image data of the menu item.
     *
     * @param imageData the image data of the menu item
     */
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    /**
     * Compares this menu item to another object for equality.
     *
     * @param o the object to compare to
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
        MenuItems menuItems = (MenuItems) o;
        return id == menuItems.id
                &&
                restaurantId == menuItems.restaurantId
                &&
                categoryId == menuItems.categoryId
                &&
                Objects.equals(foodName, menuItems.foodName)
                &&
                Objects.equals(description, menuItems.description)
                &&
                Objects.equals(isAvailable, menuItems.isAvailable)
                &&
                Objects.equals(price, menuItems.price)
                &&
                Objects.deepEquals(imageData, menuItems.imageData);
    }

    /**
     * Returns the hash code for this menu item.
     *
     * @return the hash code for this menu item
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantId, categoryId, foodName, description, isAvailable, price, Arrays.hashCode(imageData));
    }
}
