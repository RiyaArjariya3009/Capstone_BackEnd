package com.restaurant.userservice.repositories;


import com.restaurant.userservice.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    // Custom query to find a wallet by user ID
    Wallet findByUserId(int userId);
}
