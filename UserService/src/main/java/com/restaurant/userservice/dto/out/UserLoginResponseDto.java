package com.restaurant.userservice.dto.out;

/**
 * Data Transfer Object for user login responses.
 * This DTO contains user information that is returned upon a successful login.
 */
public class UserLoginResponseDto {

    /** The unique identifier for the user. */
    private int userId;

    /** The username of the user. */
    private String username;

    /** The email of the user. */
    private String email;

    /** The role of the user. */
    private String role;

    /** A response message, typically used to indicate the login status. */
    private String message;

    /**
     * Default constructor.
     */
    public UserLoginResponseDto() {
        super();
    }

    /**
     * Constructs a new UserLoginResponseDto with the specified user details.
     *
     * @param userId   the unique identifier for the user
     * @param username the username of the user
     * @param email    the email of the user
     * @param role     the role of the user
     * @param message  a response message, typically used to indicate the login status
     */
    public UserLoginResponseDto(int userId, String username, String email, String role, String message) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.message = message;
    }

    /**
     * Gets the unique identifier for the user.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param userId the new user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email of the user.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the new role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the response message.
     *
     * @return the response message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the response message.
     *
     * @param message the new response message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
