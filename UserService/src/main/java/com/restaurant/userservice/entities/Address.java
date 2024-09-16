package com.restaurant.userservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class representing an address.
 */
@Entity
@Table(name = "address")
public class Address {

    /** The unique identifier for the address. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** The unique identifier for the user associated with the address. */
    @Column(name = "user_id", nullable = false)
    private int userId;

    /** The street of the address. */
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
    public Address() {
        super();
    }

    /**
     * Constructs a new Address with the specified details.
     *
     * @param userId    the unique identifier for the user associated with the address
     * @param street    the street of the address
     * @param city      the city of the address
     * @param state     the state of the address
     * @param zipCode   the zip code of the address
     * @param country   the country of the address
     */
    public Address(int userId, String street, String city, String state, String zipCode, String country) {
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
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the address.
     *
     * @param id the new address ID
     */
    public void setId(int id) {
        this.id = id;
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
     * Gets the street of the address.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street of the address.
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
