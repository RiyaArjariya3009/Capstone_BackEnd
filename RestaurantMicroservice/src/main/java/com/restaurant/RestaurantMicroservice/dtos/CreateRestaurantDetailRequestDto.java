package com.restaurant.RestaurantMicroservice.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for creating restaurant details.
 * Contains fields for storing restaurant information with validation constraints to ensure data integrity.
 */
public class CreateRestaurantDetailRequestDto {

    /**
     * The ID of the user who owns the restaurant.
     * Must not be null.
     */
    @NotNull(message = "User id cannot be blank")
    private int userId;

    /**
     * The name of the restaurant.
     * Must not be blank, should be at least 3 characters long, and contain only alphabetic characters.
     */
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain only alphabetic characters")
    private String restaurantName;

    /**
     * The address of the restaurant.
     * Must not be blank.
     */
    @NotBlank(message = "Address cannot be blank")
    private String address;

    /**
     * The contact information for the restaurant.
     * Must be exactly 10 digits long and start with '9', '8', or '6'.
     */
    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 10, max = 10, message = "Contact Information must be exactly 10 digits")
    @Pattern(regexp = "^[986].*", message = "Contact Information must start with '9', '8', or '6'")
    private String contactInformation;

    /**
     * The opening hours of the restaurant.
     * Must not be null.
     */
    @NotNull(message = "Opening Hour cannot be blank")
    private String openingHours;

    /**
     * A brief description of the restaurant.
     * Must not be blank and should be less than 255 characters long.
     */
    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    /**
     * Retrieves the user ID.
     * @return the user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     * @param userId the user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the name of the restaurant.
     * @return the restaurant name.
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Sets the name of the restaurant. Trims any extra spaces and ensures proper formatting.
     * @param restaurantName the restaurant name to set.
     */
    public void setRestaurantName(String restaurantName) {
        if (restaurantName != null) {
            this.restaurantName = restaurantName.trim().replaceAll("\\s+", " ");
        } else {
            this.restaurantName = null;
        }
    }

    /**
     * Retrieves the address of the restaurant.
     * @return the restaurant address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the restaurant.
     * @param address the address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the contact information of the restaurant.
     * @return the contact information.
     */
    public String getContactInformation() {
        return contactInformation;
    }

    /**
     * Sets the contact information of the restaurant.
     * @param contactInformation the contact information to set.
     */
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * Retrieves the opening hours of the restaurant.
     * @return the opening hours.
     */
    public String getOpeningHours() {
        return openingHours;
    }

    /**
     * Sets the opening hours of the restaurant.
     * @param openingHours the opening hours to set.
     */
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * Retrieves the description of the restaurant.
     * @return the restaurant description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the restaurant.
     * @param description the description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Compares this CreateRestaurantDetailRequestDto to another object for equality.
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
        CreateRestaurantDetailRequestDto that = (CreateRestaurantDetailRequestDto) o;
        return userId == that.userId
                &&
                Objects.equals(restaurantName, that.restaurantName)
                &&
                Objects.equals(address, that.address)
                &&
                Objects.equals(contactInformation, that.contactInformation)
                &&
                Objects.equals(openingHours, that.openingHours)
                &&
                Objects.equals(description, that.description);
    }

    /**
     * Generates a hash code for the CreateRestaurantDetailRequestDto.
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId, restaurantName, address, contactInformation, openingHours, description);
    }

    /**
     * Returns a string representation of the CreateRestaurantDetailRequestDto.
     * @return a string representation of the object.
     */

    @Override
    public String toString() {
        return "CreateRestaurantDetailRequestDto{"
                + "userId=" + userId
                + ", restaurantName='" + restaurantName + '\''
                + ", address='" + address + '\''
                + ", contactInformation='" + contactInformation + '\''
                + ", openingHours='" + openingHours + '\''
                + ", description='" + description + '\''
                + '}';
    }


}
