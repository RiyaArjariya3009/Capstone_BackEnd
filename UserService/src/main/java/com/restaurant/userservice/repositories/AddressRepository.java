package com.restaurant.userservice.repositories;

import com.restaurant.userservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on {@link Address} entities.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    /**
     * Finds a list of {@link Address} entities associated with a specific user ID.
     *
     * @param userId the ID of the user whose addresses are to be retrieved
     * @return a list of addresses associated with the given user ID
     */
    List<Address> findByUserId(int userId);

    /**
     * Counts the number of {@link Address} entities associated with a specific user ID.
     *
     * @param userId the ID of the user whose addresses are to be counted
     * @return the number of addresses associated with the given user ID
     */
    int countByUserId(int userId);

    /**
     * Deletes all {@link Address} entities associated with a specific user ID.
     *
     * @param userId the ID of the user whose addresses are to be deleted
     */
    void deleteByUserId(int userId);
}
