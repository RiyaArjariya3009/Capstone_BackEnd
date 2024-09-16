package com.restaurant.userservice.repositories;

import com.restaurant.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on {@link User} entities.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a {@link User} by their email.
     *
     * @param email the email of the user to be retrieved
     * @return an {@link Optional} containing the user if found, or an empty {@link Optional} if not
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user with the given email exists.
     *
     * @param email the email of the user to check
     * @return {@code true} if a user with the given email exists, {@code false} otherwise
     */
    Boolean existsByEmail(String email);

    /**
     * Finds a {@link User} by their email and password.
     *
     * @param email the email of the user to be retrieved
     * @param password the password of the user to be retrieved
     * @return the user if found, or {@code null} if not
     */
    User findByEmailAndPassword(String email, String password);

    /**
     * Checks if a user with the given contact number exists.
     *
     * @param contactNumber the contact number of the user to check
     * @return {@code true} if a user with the given contact number exists, {@code false} otherwise
     */
    boolean existsByContactNumber(String contactNumber);
}
