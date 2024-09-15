package com.restaurant.RestaurantMicroservice.dtos;

import com.restaurant.RestaurantMicroservice.enums.RoleType;

import java.util.Objects;

/**
 * DTO for representing a user in the system.
 */
public class UserResponseDto {

    /**
     * The unique identifier of the user.
     */
    private int id;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The role of the user, such as 'customer' or 'restaurant_owner'.
     */
    private RoleType role;

    /**
     * Default constructor.
     */
    public UserResponseDto() {

    }

    /**
     * Parameterized constructor.
     *
     * @param id the unique identifier of the user.
     * @param username the username of the user.
     * @param email the email address of the user.
     * @param role the role of the user.
     */
    public UserResponseDto(int id, String username, String email, RoleType role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return the unique identifier of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the unique identifier of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the email address of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role of the user.
     */
    public RoleType getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role of the user.
     */
    public void setRole(RoleType role) {
        this.role = role;
    }

    /**
     * Compares this object with the specified object for equality.
     *
     * @param o the object to compare with.
     * @return {@code true} if this object is the same as the specified object;
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserResponseDto that = (UserResponseDto) o;
        return id == that.id
                && Objects.equals(username, that.username)
                && Objects.equals(email, that.email)
                && role == that.role;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, role);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "UserResponseDto{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", email='" + email + '\''
                + ", role=" + role
                + '}';
    }
}
