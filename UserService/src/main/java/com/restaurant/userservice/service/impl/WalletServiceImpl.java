package com.restaurant.userservice.service.impl;

import com.restaurant.userservice.dto.in.WalletRequestDto;
import com.restaurant.userservice.dto.out.WalletResponseDto;
import com.restaurant.userservice.entities.Wallet;
import com.restaurant.userservice.repositories.WalletRepository;
import com.restaurant.userservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public WalletResponseDto updateWallet(WalletRequestDto requestDTO) {
        Optional<Wallet> optionalWallet = walletRepository.findById(requestDTO.getWalletId());

        if (optionalWallet.isPresent()) {
            Wallet wallet = optionalWallet.get();
            wallet.setBalance(requestDTO.getBalance());
//            wallet.setUpdatedAt(LocalDateTime.now());
            Wallet updatedWallet = walletRepository.save(wallet);

            return new WalletResponseDto(
                    updatedWallet.getId(),
                    updatedWallet.getBalance(),
//                    updatedWallet.getUpdatedAt(),
                    "Wallet updated successfully"
            );
        } else {
            throw new IllegalArgumentException("Wallet not found");
        }
    }
}

