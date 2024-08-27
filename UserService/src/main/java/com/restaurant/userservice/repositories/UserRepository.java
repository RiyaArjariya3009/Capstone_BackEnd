package com.restaurant.userservice.repositories;

import com.restaurant.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
   Boolean existsByEmail(String email);
    //UserLoginResponseDto loginUser(UserLoginDto userLoginDto);
   // Custom query to find a user by email
   /*Optional<User> findByEmail(String email);

    // Custom query to check if an email already exists
    boolean existsByEmail(String email);*/

}


