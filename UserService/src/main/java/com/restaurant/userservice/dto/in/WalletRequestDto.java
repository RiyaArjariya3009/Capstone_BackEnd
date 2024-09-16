package com.restaurant.userservice.dto.in;

import java.math.BigDecimal;

public class WalletRequestDto {

    private int walletId;
    private BigDecimal balance;

    public WalletRequestDto() {
    }

    public WalletRequestDto(int walletId, BigDecimal balance) {
        this.walletId = walletId;
        this.balance = balance;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
