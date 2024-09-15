package com.restaurant.RestaurantMicroservice.dtos;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents the response DTO for creating a restaurant detail.
 * It includes details such as restaurant name, address, contact number,
 * owner name, description, and status of the restaurant (open or closed).
 */
public final class CreateRestaurantDetailResponseDto {

    /** Name of the restaurant. */
    private String restaurantName;

    /** Address of the restaurant. */
    private String address;

    /** Message associated with the response (optional). */
    private String message;

    /** Name of the restaurant owner. */
    private String ownerName;

    /** Whether the restaurant is currently open or closed. */
    private Boolean open;

    /** Contact number of the restaurant. */
    private String contactNo;

    /** Description of the restaurant. */
    private String description;

    /** Image data representing the restaurant's image. */
    private byte[] imageData;

    /**
     * Gets the address of the restaurant.
     * @return the address of the restaurant.
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
     * Gets the name of the restaurant.
     * @return the name of the restaurant.
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Sets the name of the restaurant.
     * @param restaurantName the name to set.
     */
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    /**
     * Gets the name of the restaurant owner.
     * @return the name of the owner.
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets the name of the restaurant owner.
     * @param ownerName the owner's name to set.
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Gets the message associated with the response.
     * @return the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message for the response.
     * @param message the message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the current status of the restaurant (open or closed).
     * @return the open status.
     */
    public Boolean getOpen() {
        return open;
    }

    /**
     * Sets the open status of the restaurant.
     * @param open the status to set (true if open, false otherwise).
     */
    public void setOpen(Boolean open) {
        this.open = open;
    }

    /**
     * Gets the contact number of the restaurant.
     * @return the contact number.
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * Sets the contact number of the restaurant.
     * @param contactNo the contact number to set.
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * Gets the description of the restaurant.
     * @return the description of the restaurant.
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
     * Gets the image data for the restaurant.
     * @return the image data as a byte array.
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * Sets the image data for the restaurant.
     * @param imageData the byte array representing the image data.
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
        CreateRestaurantDetailResponseDto that = (CreateRestaurantDetailResponseDto) o;
        return Objects.equals(restaurantName, that.restaurantName)
                && Objects.equals(address, that.address)
                && Objects.equals(message, that.message)
                && Objects.equals(ownerName, that.ownerName)
                && Objects.equals(open, that.open)
                && Objects.equals(contactNo, that.contactNo)
                && Objects.equals(description, that.description)
                && Arrays.equals(imageData, that.imageData);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(restaurantName, address, message, ownerName, open, contactNo, description);
        result = 31 * result + Arrays.hashCode(imageData);
        return result;
    }

    @Override
    public String toString() {
        return "CreateRestaurantDetailResponseDto{"
                + "restaurantName='" + restaurantName + '\''
                + ", address='" + address + '\''
                + ", message='" + message + '\''
                + ", ownerName='" + ownerName + '\''
                + ", open=" + open
                + ", contactNo='" + contactNo + '\''
                + ", description='" + description + '\''
                + ", imageData=" + Arrays.toString(imageData)
                + '}';
    }
}
