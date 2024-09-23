package com.restaurant.RestaurantMicroservice.dtos;

import java.util.Arrays;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for restaurant response.
 * <p>
 * This class encapsulates the data returned in the response for restaurant-related operations.
 * </p>
 */
public final class RestaurantResponseDto {

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
     * The status indicating if the restaurant is currently open.
     */
    private Boolean open;

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
    private byte[] imageUrl;

    /**
     * The unique identifier of the owner of the restaurant.
     */
    private int ownerId;

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
     * Gets the unique identifier of the owner of the restaurant.
     *
     * @return the owner identifier
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the unique identifier of the owner of the restaurant.
     *
     * @param ownerId the owner identifier to set
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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
     * Gets the status indicating if the restaurant is currently open.
     *
     * @return {@code true} if the restaurant is open; {@code false} otherwise
     */
    public Boolean getOpen() {
        return open;
    }

    /**
     * Sets the status indicating if the restaurant is currently open.
     *
     * @param open {@code true} if the restaurant is open; {@code false} otherwise
     */
    public void setOpen(Boolean open) {
        this.open = open;
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
    public byte[] getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image data of the restaurant.
     *
     * @param imageUrl the image data to set
     */
    public void setImageUrl(byte[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RestaurantResponseDto that = (RestaurantResponseDto) o;
        return id == that.id
                &&
                ownerId == that.ownerId
                &&
                Objects.equals(restaurantName, that.restaurantName)
                &&
                Objects.equals(email, that.email)
                &&
                Objects.equals(address, that.address)
                &&
                Objects.equals(contactNo, that.contactNo)
                &&
                Objects.equals(open, that.open)
                &&
                Objects.equals(openingHours, that.openingHours)
                &&
                Objects.deepEquals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantName, email, address, contactNo, open,
                openingHours, Arrays.hashCode(imageUrl), ownerId);
    }

    @Override
    public String toString() {
        return "RestaurantResponseDto{"
                + "id=" + id
                + ", restaurantName='" + restaurantName + '\''
                + ", email='" + email + '\''
                + ", address='" + address + '\''
                + ", contactNo='" + contactNo + '\''
                + ", open=" + open
                + ", openingHours='" + openingHours + '\''
                + ", imageUrl=" + Arrays.toString(imageUrl)
                + ", ownerId=" + ownerId
                + '}';
    }


}
