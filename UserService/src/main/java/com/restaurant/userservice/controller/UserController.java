package com.restaurant.userservice.controller;

import com.restaurant.userservice.dto.in.AddressRequestDto;
import com.restaurant.userservice.dto.in.UserLoginRequestDto;
import com.restaurant.userservice.dto.out.AddressResponseDto;
import com.restaurant.userservice.dto.out.UserLoginResponseDto;
import com.restaurant.userservice.dto.in.UserRegistrationRequestDto;
import com.restaurant.userservice.dto.out.UserRegistrationResponseDto;
import com.restaurant.userservice.entities.User;
import com.restaurant.userservice.service.AddressService;
import com.restaurant.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for handling user authentication requests.
 * Provides endpoints for user registration and login.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequestDto userDto) {
        logger.info("Attempting to register user with email: {}", userDto.getEmail());
        UserRegistrationResponseDto newUser = userService.registerUser(userDto);
        logger.info("User registered successfully with email: {}", userDto.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> loginUser(@Valid @RequestBody UserLoginRequestDto userLoginDto) {
        logger.info("Attempting to login user with email: {}", userLoginDto.getEmail());
        UserLoginResponseDto userResponse = userService.loginUser(userLoginDto);
        logger.info("User logged in successfully with email: {}", userLoginDto.getEmail());
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public  ResponseEntity<User> getAllUsers(@PathVariable String email) {
        User user= userService.getAllUsers(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

   @PostMapping("/{userId}/addAddresses")
   public ResponseEntity<AddressResponseDto> addAddress(
           @PathVariable  int userId, @RequestBody AddressRequestDto addressAddDTO) {

            AddressResponseDto responseDTO = addressService.addAddress(userId, addressAddDTO);
           return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

           //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add address", e);

   }

    @PutMapping("/{addressId}/update")
    public ResponseEntity<AddressResponseDto> updateAddress(
            @PathVariable  int addressId,
            @RequestBody   AddressRequestDto addressUpdateDTO){

            AddressResponseDto responseDTO = addressService.updateAddress(addressId, addressUpdateDTO);
            return ResponseEntity.ok(responseDTO);

    }

    @DeleteMapping("/{addressId}/delete")
    public ResponseEntity<AddressResponseDto> deleteAddress(@PathVariable  int addressId){
            AddressResponseDto responseDTO = addressService.deleteAddress(addressId);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }


    @GetMapping("/{userId}/addresses")
    public ResponseEntity<AddressResponseDto> getAllAddresses(@PathVariable  int userId) {

            AddressResponseDto responseDTO = addressService.getAllAddressesByUserId(userId);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


}






