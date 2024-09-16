package com.restaurant.userservice.service.impl;

import com.restaurant.userservice.dto.in.AddressRequestDto;
import com.restaurant.userservice.dto.out.AddressResponseDto;
import com.restaurant.userservice.entities.Address;
import com.restaurant.userservice.entities.User;
import com.restaurant.userservice.enums.RoleType;
import com.restaurant.userservice.repositories.AddressRepository;
import com.restaurant.userservice.repositories.UserRepository;
import com.restaurant.userservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;
    private final AddressRepository deliveryAddressRepository;

    @Autowired
    public AddressServiceImpl(UserRepository userRepository, AddressRepository deliveryAddressRepository) {
        this.userRepository = userRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }


    @Override
    public AddressResponseDto addAddress(int userId, AddressRequestDto addressAddDTO) {

        // Fetch user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if user has the role 'CUSTOMER'
        if (!user.getRole().equals(RoleType.CUSTOMER)) {
            //throw new UnauthorizedAccessException("User is not permitted to add an address");
        }

        Address deliveryAddress = new Address();
        deliveryAddress.setUserId(userId);
        deliveryAddress.setStreet(addressAddDTO.getStreet());
        deliveryAddress.setCity(addressAddDTO.getCity());
        deliveryAddress.setState(addressAddDTO.getState());
        deliveryAddress.setCountry(addressAddDTO.getCountry());
        deliveryAddress.setZipCode(addressAddDTO.getZipCode());

        //deliveryAddress.setPostalCode(addressAddDTO.getPostalCode());


        deliveryAddressRepository.save(deliveryAddress);

        List<Address> addressList = deliveryAddressRepository.findByUserId(userId);

        List<AddressRequestDto> addressDTOList = addressList.stream()
                .map(address -> new AddressRequestDto(
                       // address.getId(),
                        address.getStreet(),
                        address.getCity(),
                        address.getState(),
                        address.getZipCode(),
                        address.getCountry()


                ))
                .collect(Collectors.toList());

        return new AddressResponseDto(userId, addressDTOList);

    }
    @Override
    public AddressResponseDto updateAddress(int addressId, AddressRequestDto addressUpdateDTO) {

        // Find the address by ID
        Address deliveryAddress = deliveryAddressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // Find the user by ID associated with the address
        User user = userRepository.findById(deliveryAddress.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user has the appropriate role
        if (user.getRole() != RoleType.CUSTOMER) {
            throw new RuntimeException("User not permitted to update address");
        }

        // Update the address details
        deliveryAddress.setStreet(addressUpdateDTO.getStreet());
        deliveryAddress.setCity(addressUpdateDTO.getCity());
        deliveryAddress.setState(addressUpdateDTO.getState());
        deliveryAddress.setZipCode(addressUpdateDTO.getZipCode());
        deliveryAddress.setCountry(addressUpdateDTO.getCountry());


        deliveryAddressRepository.save(deliveryAddress);


        List<AddressRequestDto> updatedAddresses = deliveryAddressRepository.findByUserId(user.getId())
                .stream()
                .map(address -> new AddressRequestDto(
                        address.getStreet(),
                        address.getCity(),
                        address.getState(),
                        address.getZipCode(),
                        address.getCountry()))
                .collect(Collectors.toList());

        // Return the AddressResponseDto with the updated list of addresses
        return new AddressResponseDto(user.getId(), updatedAddresses);
    }


    @Override
    public AddressResponseDto deleteAddress(int addressId) {

       /* Address deliveryAddress = deliveryAddressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));


        deliveryAddressRepository.delete(deliveryAddress);


        return new AddressResponseDto("Address deleted successfully");*/
        // Find the address by ID
        Address deliveryAddress = deliveryAddressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // Get the userId before deleting the address
        //int userId = deliveryAddressRepository.getId();
        int userId=1;

        // Delete the address
        deliveryAddressRepository.delete(deliveryAddress);

        // Fetch the updated list of addresses for the user
        List<AddressRequestDto> updatedAddresses = deliveryAddressRepository.findByUserId(userId)
                .stream()
                .map(address -> new AddressRequestDto(
                        address.getStreet(),
                        address.getCity(),
                        address.getState(),
                        address.getZipCode(),
                        address.getCountry()))
                .collect(Collectors.toList());

        // Return the AddressResponseDto with the updated list of addresses
        return new AddressResponseDto(userId, updatedAddresses);
    }

    @Override
    public AddressResponseDto getAllAddressesByUserId(int userId) {


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        List<Address> addresses = deliveryAddressRepository.findByUserId(userId);


        List<AddressRequestDto> addressDTOs = addresses.stream()
                .map(address -> new AddressRequestDto(
                   //     address.getId(),
                        address.getCity(),
                        address.getCountry(),
                        address.getStreet(),
                        address.getZipCode(),
                        address.getState()
                      //  address.getStreetAddress()
                ))
                .collect(Collectors.toList());


        return new AddressResponseDto(userId, addressDTOs);
    }

}

