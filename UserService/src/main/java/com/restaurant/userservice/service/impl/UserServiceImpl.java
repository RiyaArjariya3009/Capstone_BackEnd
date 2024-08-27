package com.restaurant.userservice.service.impl;

import com.restaurant.userservice.dto.in.UserLoginRequestDto;
import com.restaurant.userservice.dto.out.UserLoginResponseDto;
import com.restaurant.userservice.dto.in.UserRegistrationRequestDto;
import com.restaurant.userservice.dto.out.UserRegistrationResponseDto;
//import com.restaurant.userservice.entities.Address;
import com.restaurant.userservice.entities.User;
import com.restaurant.userservice.entities.Wallet;
import com.restaurant.userservice.enums.RoleType;
import com.restaurant.userservice.exceptions.InvalidCredentialsException;
import com.restaurant.userservice.exceptions.InvalidRoleException;
import com.restaurant.userservice.exceptions.UserExistsException;
import com.restaurant.userservice.exceptions.UserNotFoundException;
//import com.restaurant.userservice.repositories.AddressRepository;
import com.restaurant.userservice.repositories.UserRepository;
import com.restaurant.userservice.repositories.WalletRepository;
import com.restaurant.userservice.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    /* @Autowired
     private UserRepository userRepository;

     @Autowired
     private WalletRepository walletRepository;*/
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }



 /*   @Override
    public Optional<User> findByEmail(String email) {
        logger.info("Finding user by email: " + email);
        return userRepository.findByEmail(email);
    }*/

    @Override
    public UserRegistrationResponseDto registerUser(UserRegistrationRequestDto requestDTO) {

        Optional<User> isUserExists = userRepository.findByEmail(requestDTO.getEmail());
        if (isUserExists.isPresent()) {
            logger.warn("User with email " + requestDTO.getEmail() + " already exists");
            throw new UserExistsException("A user with this email already exists");
        }
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(Base64.getEncoder().encodeToString(requestDTO.getPassword().getBytes()));
        // user.setRole(RoleType.valueOf(requestDTO.getRole()));


        user.setRole(requestDTO.getRole());

        userRepository.save(user);

        BigDecimal walletBalance = null;
        if (user.getRole() == RoleType.CUSTOMER) {
            Wallet wallet = new Wallet();
            wallet.setUserId(user.getId());
            wallet.setBalance(BigDecimal.valueOf(1000.00));
            walletRepository.save(wallet);

            walletBalance = wallet.getBalance();
        }

        UserRegistrationResponseDto responseDTO = new UserRegistrationResponseDto();
        //  responseDTO.setUserId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setRole(user.getRole().toString());
        responseDTO.setWalletBalance(walletBalance);
        responseDTO.setPassword(user.getPassword());
        // responseDTO.setMessage("Registration successful");

        return responseDTO;


    }

    @Override
    public UserLoginResponseDto loginUser(UserLoginRequestDto loginRequest) {


        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> {
                    logger.warn("User with email " + loginRequest.getEmail() + " not found");
                    return new UserNotFoundException("User with email " + loginRequest.getEmail() + " not found");
                });


        String decodedPassword = new String(Base64.getDecoder().decode(user.getPassword()));

        // Validate password
        if (!loginRequest.getPassword().equals(decodedPassword)) {
            logger.warn("Invalid password attempt for email: " + loginRequest.getEmail());
            throw new InvalidCredentialsException("Invalid email or password");
        }



        UserLoginResponseDto responseDTO = new UserLoginResponseDto();
        responseDTO.setUsername(user.getUsername());
        responseDTO.setRole(user.getRole().name());
        responseDTO.setEmail(user.getEmail());


        return responseDTO;

    }

    private boolean isValidRole(RoleType requestedRole, RoleType actualRole) {
        return requestedRole == actualRole;
    }

    @Override
    public User getAllUsers(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email : " + email + " not found"));

        return user;


    }


}