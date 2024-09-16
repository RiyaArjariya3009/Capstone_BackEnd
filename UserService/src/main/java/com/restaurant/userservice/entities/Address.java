package com.restaurant.userservice.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

   // @NotBlank(message = "Street is mandatory")
    private String street;

   // @NotBlank(message = "City is mandatory")
    private String city;

    //@NotBlank(message = "State is mandatory")
    private String state;

   // @NotBlank(message = "Zip code is mandatory")
    private String zipCode;

   // @NotBlank(message = "Country is mandatory")
    private String country;


    public Address() {
        super();
    }

    public Address(int userId,String street, String city, String state, String zipCode, String country) {
        this.userId=userId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUserId(int userId)
    {
        this.userId=userId;
    }
    public int getUserId()
    {
        return userId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}

/*public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "city", nullable = false, length = 255)
    private String city;

    @Column(name = "country", nullable = false, length = 255)
    private String country;

    @Column(name = "full_Name", nullable = false, length = 255)
    private String fullName;

    @Column(name = "postal_code", nullable = false, length = 255)
    private String postalCode;

    @Column(name = "state", nullable = false, length = 255)
    private String state;

    @Column(name = "street_address", nullable = false, length = 255)
    private String streetAddress;

    public Address() {
        super();
    }

    public Address(int userId, String city, String country, String fullName, String postalCode, String state, String streetAddress) {
        this.userId = userId;
        this.city = city;
        this.country = country;
        this.fullName = fullName;
        this.postalCode = postalCode;
        this.state = state;
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    @Override
    public String toString() {
        return "DeliveryAddress{" +
                "id=" + id +
                ", userId=" + userId +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", fullName='" + fullName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", state='" + state + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                '}';
    }
}*/

