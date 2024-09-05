package com.restaurant.userservice.dto.out;

/**
 * Data Transfer Object for user registration responses.
 * This DTO contains user information returned after a successful registration.
 */
public class UserRegistrationResponseDto {

    /** The unique identifier for the user. */
    private int userId;

    /** The username of the user. */
    private String username;

    /** The password of the user. */
    private String password;

    /** The email of the user. */
    private String email;

    /** The wallet balance associated with the user. */
    private Double walletBalance;

    /** The role assigned to the user. */
    private String role;

    /** A response message, typically used to indicate the registration status. */
    private String message;

    /**
     * Default constructor.
     */
    public UserRegistrationResponseDto() {
        super();
    }

    /**
     * Constructs a new UserRegistrationResponseDto with the specified user details.
     *
     * @param username      the username of the user
     * @param password      the password of the user
     * @param email         the email of the user
     * @param role          the role of the user
     * @param walletBalance the wallet balance associated with the user
     * @param message       a response message, typically used to indicate the registration status
     */
    public UserRegistrationResponseDto(
            String username,
            String password,
            String email,
            String role,
            Double walletBalance,
            String message) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.walletBalance = walletBalance;
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
     * Gets the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Gets the wallet balance associated with the user.
     *
     * @return the wallet balance
     */
    public Double getWalletBalance() {
        return walletBalance;
    }

    /**
     * Sets the wallet balance associated with the user.
     *
     * @param walletBalance the new wallet balance
     */
    public void setWalletBalance(Double walletBalance) {
        this.walletBalance = walletBalance;
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
