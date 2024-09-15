package com.ordermicroservice.OrderMicroservice.dtos;
/**
 * Data Transfer Object for Address response.
 * This DTO contains address details associated with a user.
 */
public class AddressResponseDto {

    /** The unique identifier for the address. */
    private int addressId;

    /** The unique identifier for the user associated with the address. */
    private int userId;

    /** The street information of the address. */
    private String street;

    /** The city of the address. */
    private String city;

    /** The state of the address. */
    private String state;

    /** The zip code of the address. */
    private String zipCode;

    /** The country of the address. */
    private String country;

    /**
     * Default constructor.
     */
    public AddressResponseDto() {
        super();
    }

    /**
     * Parameterized constructor to initialize all fields.
     *
     * @param addressId the unique identifier for the address
     * @param userId the unique identifier for the user associated with the address
     * @param street the street information of the address
     * @param city the city of the address
     * @param state the state of the address
     * @param zipCode the zip code of the address
     * @param country the country of the address
     */
    public AddressResponseDto(
            int addressId, int userId, String street, String city,
            String state, String zipCode, String country
    ) {
        this.addressId = addressId;
        this.userId = userId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    /**
     * Gets the unique identifier for the address.
     *
     * @return the address ID
     */
    public int getAddressId() {
        return addressId;
    }

    /**
     * Sets the unique identifier for the address.
     *
     * @param addressId the new address ID
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    /**
     * Gets the unique identifier for the user associated with the address.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the user associated with the address.
     *
     * @param userId the new user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the street information of the address.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street information of the address.
     *
     * @param street the new street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the city of the address.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the address.
     *
     * @param city the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state of the address.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state of the address.
     *
     * @param state the new state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the zip code of the address.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code of the address.
     *
     * @param zipCode the new zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the country of the address.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the address.
     *
     * @param country the new country
     */
    public void setCountry(String country) {
        this.country = country;
    }
}

