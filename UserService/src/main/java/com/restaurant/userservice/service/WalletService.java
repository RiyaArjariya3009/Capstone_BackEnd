package com.restaurant.userservice.service;


import com.restaurant.userservice.dto.in.WalletRequestDto;
import com.restaurant.userservice.dto.out.WalletResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {

    public WalletResponseDto updateWallet(WalletRequestDto requestDTO);
}
