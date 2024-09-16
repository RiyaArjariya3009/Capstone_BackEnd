package com.restaurant.userservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;

    @Column(name="user_id",nullable=false)
    public int userId;

    @Column(nullable=false)
    private BigDecimal balance;

    public Wallet()
    {
        super();
    }

    public Wallet(int userId, BigDecimal balance) {
        this.userId = userId;
        this.balance = balance;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
