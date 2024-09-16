package com.restaurant.userservice.dto.out;

import java.math.BigDecimal;

public class WalletResponseDto {

    private int walletId;
    private BigDecimal balance;
    private String message;

    public WalletResponseDto(int walletId, BigDecimal balance, String message) {
        this.walletId = walletId;
        this.balance = balance;
//        this.updatedAt = updatedAt;
        this.message = message;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    // Getters and Setters
}
