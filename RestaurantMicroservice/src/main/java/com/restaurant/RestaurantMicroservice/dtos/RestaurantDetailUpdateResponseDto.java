package com.restaurant.RestaurantMicroservice.dtos;

import javax.persistence.Lob;
import java.util.Arrays;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for updating restaurant details response.
 * <p>
 * This class is used to encapsulate the data returned after updating restaurant details.
 * </p>
 */
public final class RestaurantDetailUpdateResponseDto {

    /**
     * The unique identifier of the restaurant.
     */
    private int id;

    /**
     * The name of the restaurant.
     */
    private String restaurantName;

    /**
     * The email address associated with the restaurant.
     */
    private String email;

    /**
     * The address of the restaurant.
     */
    private String address;

    /**
     * The contact number of the restaurant.
     */
    private String contactNo;

    /**
     * The opening hours of the restaurant.
     */
    private String openingHours;

    /**
     * The image data of the restaurant.
     * <p>
     * Stored as a byte array to accommodate large binary data.
     * </p>
     */
    @Lob
    private byte[] imageData;

    /**
     * A message associated with the update operation.
     */
    private String message;

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the unique identifier of the restaurant.
     *
     * @return the unique identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the restaurant.
     *
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the opening hours of the restaurant.
     *
     * @return the opening hours
     */
    public String getOpeningHours() {
        return openingHours;
    }

    /**
     * Sets the opening hours of the restaurant.
     *
     * @param openingHours the opening hours to set
     */
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * Gets the contact number of the restaurant.
     *
     * @return the contact number
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * Sets the contact number of the restaurant.
     *
     * @param contactNo the contact number to set
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * Gets the address of the restaurant.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the restaurant.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the email address of the restaurant.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the restaurant.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the name of the restaurant.
     *
     * @return the restaurant name
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Sets the name of the restaurant.
     *
     * @param restaurantName the restaurant name to set
     */
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    /**
     * Gets the image data of the restaurant.
     *
     * @return the image data
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * Sets the image data of the restaurant.
     *
     * @param imageData the image data to set
     */
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RestaurantDetailUpdateResponseDto that = (RestaurantDetailUpdateResponseDto) o;
        return id == that.id
                &&
                Objects.equals(restaurantName, that.restaurantName)
                &&
                Objects.equals(email, that.email)
                &&
                Objects.equals(address, that.address)
                &&
                Objects.equals(contactNo, that.contactNo)
                &&
                Objects.equals(openingHours, that.openingHours)
                &&
                Objects.deepEquals(imageData, that.imageData)
                &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantName, email, address, contactNo, openingHours, Arrays.hashCode(imageData), message);
    }

    @Override
    public String toString() {
        return "RestaurantDetailUpdateResponseDto{"
                + "id=" + id
                + ", restaurantName='" + restaurantName + '\''
                + ", email='" + email + '\''
                + ", address='" + address + '\''
                + ", contactNo='" + contactNo + '\''
                + ", openingHours='" + openingHours + '\''
                + ", imageData=" + Arrays.toString(imageData)
                + ", message='" + message + '\''
                + '}';
    }
}
