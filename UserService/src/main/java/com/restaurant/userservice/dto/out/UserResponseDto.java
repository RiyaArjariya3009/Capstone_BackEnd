package com.restaurant.userservice.dto.out;

/**
 * Data Transfer Object for user responses.
 * This DTO contains user information returned in responses.
 */
public class UserResponseDto {

    /** The unique identifier for the user. */
    private int id;

    /** The username of the user. */
    private String username;

    /** The email address of the user. */
    private String email;

    /** The contact number of the user. */
    private String contactNumber;

    /** The role assigned to the user. */
    private String role;

    /** The wallet balance associated with the user. */
    private Double walletBalance;

    /**
     * Default constructor.
     */
    public UserResponseDto() {
        super();
    }

    /**
     * Constructs a new UserResponseDto with the specified user details.
     *
     * @param id             the unique identifier for the user
     * @param username       the username of the user
     * @param email          the email address of the user
     * @param contactNumber  the contact number of the user
     * @param role           the role assigned to the user
     * @param walletBalance  the wallet balance associated with the user
     */
    public UserResponseDto(int id, String username, String email, String contactNumber, String role, Double walletBalance) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.contactNumber = contactNumber;
        this.role = role;
        this.walletBalance = walletBalance;
    }

    /**
     * Gets the unique identifier for the user.
     *
     * @return the user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param id the new user ID
     */
    public void setId(int id) {
        this.id = id;
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
     * Gets the email address of the user.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the contact number of the user.
     *
     * @return the contact number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets the contact number of the user.
     *
     * @param contactNumber the new contact number
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Gets the role assigned to the user.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role assigned to the user.
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
}
