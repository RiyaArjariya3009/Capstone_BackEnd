package com.restaurant.RestaurantMicroservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Represents a category within a restaurant.
 */
@Entity
@Table(name = "category")
public class Category {

    /**
     * The unique identifier for the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the category.
     */
    @Column(name = "category_name", nullable = false)
    private String name;

    /**
     * The ID of the restaurant to which this category belongs.
     */
    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;

    /**
     * Default constructor.
     */
    public Category() {
        super();
    }

    /**
     * Parameterized constructor.
     *
     * @param name          the name of the category
     * @param restaurantId  the ID of the restaurant to which this category belongs
     */
    public Category(String name, int restaurantId) {
        this.name = name;
        this.restaurantId = restaurantId;
    }

    /**
     * Gets the ID of the category.
     *
     * @return the ID of the category
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the category.
     *
     * @param id the ID of the category
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the category.
     *
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name the name of the category
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the restaurant ID to which this category belongs.
     *
     * @return the restaurant ID
     */
    public int getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets the restaurant ID to which this category belongs.
     *
     * @param restaurantId the restaurant ID
     */
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Compares this category to another object for equality.
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
        Category category = (Category) o;
        return id == category.id
                &&
                restaurantId == category.restaurantId
                &&
                Objects.equals(name, category.name);
    }

    /**
     * Returns the hash code for this category.
     *
     * @return the hash code for this category
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, restaurantId);
    }
}
