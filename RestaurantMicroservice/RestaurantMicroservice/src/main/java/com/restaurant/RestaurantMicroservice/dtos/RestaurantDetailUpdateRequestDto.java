package com.restaurant.RestaurantMicroservice.dtos;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for updating restaurant details.
 * <p>
 * This class is used to encapsulate the data required to update restaurant details.
 * </p>
 */
public final class RestaurantDetailUpdateRequestDto {

    /**
     * The name of the restaurant.
     * <p>
     * Must be between 3 and 100 characters, contain only alphabetic characters and spaces.
     * </p>
     */
    @NotBlank(message = "Restaurant name cannot be blank")
    @Size(min = 3, max = 100, message = "Restaurant name must be between 3 and 100 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Restaurant name must contain only alphabetic characters and spaces")
    private String restaurantName;

    /**
     * The address of the restaurant.
     * <p>
     * Must be less than 255 characters.
     * </p>
     */
    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;

    /**
     * The opening hours of the restaurant.
     * <p>
     * Must be less than 100 characters.
     * </p>
     */
    @NotBlank(message = "Opening hours cannot be blank")
    @Size(max = 100, message = "Opening hours must be less than 100 characters")
    private String openingHours;

    /**
     * The contact information of the restaurant.
     * <p>
     * Must be exactly 10 digits and start with '9', '8', or '6'.
     * </p>
     */
    @NotBlank(message = "Contact information cannot be blank")
    @Pattern(regexp = "^[986]\\d{9}$", message = "Contact information must be exactly 10 digits and start with '9', '8', or '6'")
    private String contactInformation;

    /**
     * The image of the restaurant.
     * <p>
     * This field is required.
     * </p>
     */
    @NotNull(message = "Image is required")
    private MultipartFile image;

    /**
     * The description of the restaurant.
     * <p>
     * Must be less than 255 characters.
     * </p>
     */
    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    /**
     * Gets the restaurant name.
     *
     * @return the restaurant name
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Sets the restaurant name.
     *
     * @param restaurantName the restaurant name to set
     */
    public void setRestaurantName(String restaurantName) {
        if (restaurantName != null) {
            this.restaurantName = restaurantName.trim().replaceAll("\\s+", " ");
        } else {
            this.restaurantName = null;
        }
    }

    /**
     * Gets the opening hours.
     *
     * @return the opening hours
     */
    public String getOpeningHours() {
        return openingHours;
    }

    /**
     * Sets the opening hours.
     *
     * @param openingHours the opening hours to set
     */
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the contact information.
     *
     * @return the contact information
     */
    public String getContactInformation() {
        return contactInformation;
    }

    /**
     * Sets the contact information.
     *
     * @param contactInformation the contact information to set
     */
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the image.
     *
     * @return the image
     */
    public MultipartFile getImage() {
        return image;
    }

    /**
     * Sets the image.
     *
     * @param image the image to set
     */
    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RestaurantDetailUpdateRequestDto that = (RestaurantDetailUpdateRequestDto) o;
        return Objects.equals(restaurantName, that.restaurantName)
                &&
                Objects.equals(address, that.address)
                &&
                Objects.equals(openingHours, that.openingHours)
                &&
                Objects.equals(contactInformation, that.contactInformation)
                &&
                Objects.equals(image, that.image)
                &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantName, address, openingHours, contactInformation, image, description);
    }

    @Override
    public String toString() {
        return "RestaurantDetailUpdateRequestDto{"
                + "restaurantName='" + restaurantName + '\''
                + ", address='" + address + '\''
                + ", openingHours='" + openingHours + '\''
                + ", contactInformation='" + contactInformation + '\''
                + ", image=" + image
                + ", description='" + description + '\''
                + '}';
    }
}
