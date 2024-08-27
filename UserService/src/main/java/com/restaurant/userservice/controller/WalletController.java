package com.restaurant.userservice.controller;
import com.restaurant.userservice.dto.in.WalletRequestDto;
import com.restaurant.userservice.dto.out.WalletResponseDto;
import com.restaurant.userservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PutMapping("/update")
    public ResponseEntity<WalletResponseDto> updateWallet(@RequestBody WalletRequestDto requestDTO) {
        try {
            WalletResponseDto responseDTO = walletService.updateWallet(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wallet update failed", e);
        }
    }
}

