package com.restaurant.RestaurantMicroservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents the details of a restaurant.
 */
@Entity
@Table(name = "restaurant")
public class RestaurantDetail {

    /**
     * The unique identifier for the restaurant.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The ID of the owner of the restaurant.
     */
    @Column(name = "owner_id", nullable = false)
    private int ownerId;

    /**
     * The name of the restaurant.
     */
    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    /**
     * The address of the restaurant.
     */
    @Column(nullable = false)
    private String address;

    /**
     * The contact number of the restaurant.
     */
    @Column(name = "contact_no", nullable = false)
    private String contactNo;

    /**
     * Indicates whether the restaurant is open.
     */
    @Column(nullable = false)
    private Boolean open;

    /**
     * The opening hours of the restaurant.
     */
    @Column(name = "opening_hours", nullable = false)
    private String openingHours;

    /**
     * A description of the restaurant.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The image data of the restaurant.
     */
    @Column(name = "image_url")
    @Lob
    private byte[] imageData;

    /**
     * Default constructor.
     */
    public RestaurantDetail() {
        super();
    }

    /**
     * Parameterized constructor.
     *
     * @param id              the unique identifier for the restaurant
     * @param ownerId         the ID of the owner of the restaurant
     * @param restaurantName the name of the restaurant
     * @param address         the address of the restaurant
     * @param contactNo       the contact number of the restaurant
     * @param open            indicates whether the restaurant is open
     * @param openingHours    the opening hours of the restaurant
     * @param imageData       the image data of the restaurant
     * @param description     a description of the restaurant
     */
    public RestaurantDetail(int id, int ownerId, String restaurantName,
                            String address, String contactNo, Boolean open, String openingHours,
                            byte[] imageData, String description) {
        this.id = id;
        this.ownerId = ownerId;
        this.restaurantName = restaurantName;
        this.address = address;
        this.contactNo = contactNo;
        this.open = open;
        this.openingHours = openingHours;
        this.imageData = imageData;
        this.description = description;
    }

    /**
     * Gets the unique identifier for the restaurant.
     *
     * @return the ID of the restaurant
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the restaurant.
     *
     * @param id the ID of the restaurant
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the owner of the restaurant.
     *
     * @return the owner ID
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the ID of the owner of the restaurant.
     *
     * @param ownerId the owner ID
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Gets the name of the restaurant.
     *
     * @return the name of the restaurant
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Sets the name of the restaurant.
     *
     * @param restaurantName the name of the restaurant
     */
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    /**
     * Gets the address of the restaurant.
     *
     * @return the address of the restaurant
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the restaurant.
     *
     * @param address the address of the restaurant
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the contact number of the restaurant.
     *
     * @return the contact number of the restaurant
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * Sets the contact number of the restaurant.
     *
     * @param contactNo the contact number of the restaurant
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * Gets whether the restaurant is open.
     *
     * @return true if the restaurant is open, false otherwise
     */
    public Boolean getOpen() {
        return open;
    }

    /**
     * Sets whether the restaurant is open.
     *
     * @param open true if the restaurant is open, false otherwise
     */
    public void setOpen(Boolean open) {
        this.open = open;
    }

    /**
     * Gets the opening hours of the restaurant.
     *
     * @return the opening hours of the restaurant
     */
    public String getOpeningHours() {
        return openingHours;
    }

    /**
     * Sets the opening hours of the restaurant.
     *
     * @param openingHours the opening hours of the restaurant
     */
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * Gets the description of the restaurant.
     *
     * @return the description of the restaurant
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the restaurant.
     *
     * @param description the description of the restaurant
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the image data of the restaurant.
     *
     * @return the image data of the restaurant
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * Sets the image data of the restaurant.
     *
     * @param imageData the image data of the restaurant
     */
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    /**
     * Compares this restaurant detail to another object for equality.
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
        RestaurantDetail that = (RestaurantDetail) o;
        return id == that.id
                &&
                ownerId == that.ownerId
                &&
                Objects.equals(restaurantName, that.restaurantName)
                &&
                Objects.equals(address, that.address)
                &&
                Objects.equals(contactNo, that.contactNo)
                &&
                Objects.equals(open, that.open)
                &&
                Objects.equals(openingHours, that.openingHours)
                &&
                Objects.equals(description, that.description)
                &&
                Objects.deepEquals(imageData, that.imageData);
    }

    /**
     * Returns the hash code for this restaurant detail.
     *
     * @return the hash code for this restaurant detail
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, restaurantName, address, contactNo, open, openingHours, description,
                Arrays.hashCode(imageData));
    }
}
