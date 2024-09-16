package com.restaurant.userservice.service.impl;

import com.restaurant.userservice.constants.Constants;
import com.restaurant.userservice.dto.in.AddressRequestDto;
import com.restaurant.userservice.dto.in.AddressUpdateRequestDto;
import com.restaurant.userservice.dto.out.AddressResponseDto;
import com.restaurant.userservice.dto.out.CommonResponseDto;
import com.restaurant.userservice.entities.Address;
import com.restaurant.userservice.entities.User;
import com.restaurant.userservice.enums.RoleType;
import com.restaurant.userservice.exceptions.AddressNotFoundExceptions;
import com.restaurant.userservice.exceptions.UserNotAllowedExceptions;
import com.restaurant.userservice.exceptions.UserNotFoundException;
import com.restaurant.userservice.repositories.AddressRepository;
import com.restaurant.userservice.repositories.UserRepository;
import com.restaurant.userservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link AddressService} for managing addresses.
 */
@Service
public class AddressServiceImpl implements AddressService {

    /**
     * Repository for managing {@link User} entities.
     */
    @Autowired
    private  UserRepository userRepository;

    /**
     * Repository for managing {@link Address} entities.
     */
    @Autowired
    private  AddressRepository addressRepository;

    /**
     * Adds a new address for a user.
     *
     * @param addressAddDTO the DTO containing the address details to be added
     * @return the {@link AddressResponseDto} with the details of the added address
     * @throws UserNotFoundException if the user with the specified ID is not found
     * @throws UserNotAllowedExceptions if the user does not have the CUSTOMER role
     */
    @Override
    public AddressResponseDto addAddress(AddressRequestDto addressAddDTO) {
        User user = userRepository.findById(addressAddDTO.getId())
                .orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND));

        if (!user.getRole().equals(RoleType.CUSTOMER)) {
            throw new UserNotAllowedExceptions(Constants.USER_NOT_ALLOWED);
        }

        Address address = new Address();
        address.setUserId(user.getId());
        address.setCity(addressAddDTO.getCity());
        address.setCountry(addressAddDTO.getCountry());
        address.setZipCode(addressAddDTO.getZipCode());
        address.setState(addressAddDTO.getState());
        address.setStreet(addressAddDTO.getStreet());

        Address savedAddress = addressRepository.save(address);

        AddressResponseDto responseDTO = new AddressResponseDto();
        responseDTO.setAddressId(savedAddress.getId());
        responseDTO.setStreet(savedAddress.getStreet());
        responseDTO.setCity(savedAddress.getCity());
        responseDTO.setState(savedAddress.getState());
        responseDTO.setZipCode(savedAddress.getZipCode());
        responseDTO.setCountry(savedAddress.getCountry());
        responseDTO.setUserId(savedAddress.getUserId());

        return responseDTO;
    }

    /**
     * Retrieves an address by its ID.
     *
     * @param addressId the ID of the address to retrieve
     * @return the {@link AddressResponseDto} with the address details
     * @throws AddressNotFoundExceptions if the address with the specified ID is not found
     */
    @Override
    public AddressResponseDto getAddressById(int addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundExceptions(Constants.ADDRESS_NOT_FOUND + addressId));

        AddressResponseDto responseDTO = new AddressResponseDto();
        responseDTO.setAddressId(address.getId());
        responseDTO.setStreet(address.getStreet());
        responseDTO.setCity(address.getCity());
        responseDTO.setState(address.getState());
        responseDTO.setZipCode(address.getZipCode());
        responseDTO.setCountry(address.getCountry());
        responseDTO.setUserId(address.getUserId());

        return responseDTO;
    }

    /**
     * Updates an existing address.
     *
     * @param addressId the ID of the address to be updated
     * @param addressUpdateDTO the DTO containing the updated address details
     * @return a {@link CommonResponseDto} indicating the outcome of the update operation
     * @throws AddressNotFoundExceptions if the address with the specified ID is not found
     * @throws UserNotFoundException if the user associated with the address is not found
     * @throws UserNotAllowedExceptions if the user does not have the CUSTOMER role
     */
    @Override
    public CommonResponseDto updateAddress(int addressId, AddressUpdateRequestDto addressUpdateDTO) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundExceptions(Constants.ADDRESS_NOT_FOUND));

        User user = userRepository.findById(address.getUserId())
                .orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND));

        if (user.getRole() != RoleType.CUSTOMER) {
            throw new UserNotAllowedExceptions(Constants.USER_NOT_ALLOWED);
        }

        address.setCity(addressUpdateDTO.getCity());
        address.setCountry(addressUpdateDTO.getCountry());
        address.setZipCode(addressUpdateDTO.getZipCode());
        address.setState(addressUpdateDTO.getState());
        address.setStreet(addressUpdateDTO.getStreet());

        addressRepository.save(address);

        return new CommonResponseDto(Constants.ADDRESS_UPDATE_SUCCESSFULLY);
    }

    /**
     * Deletes an address by its ID.
     *
     * @param addressId the ID of the address to be deleted
     * @return a {@link CommonResponseDto} indicating the outcome of the delete operation
     * @throws AddressNotFoundExceptions if the address with the specified ID is not found
     */
    @Override
    public CommonResponseDto deleteAddress(int addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundExceptions(Constants.ADDRESS_NOT_FOUND));

        addressRepository.delete(address);

        return new CommonResponseDto(Constants.ADDRESS_DELETE_SUCCESSFULLY);
    }

    /**
     * Retrieves all addresses associated with a specific user ID.
     *
     * @param userId the ID of the user whose addresses are to be retrieved
     * @return a list of {@link AddressResponseDto} representing the addresses of the user
     * @throws UserNotFoundException if the user with the specified ID is not found
     */
    @Override
    public List<AddressResponseDto> getAllAddressesByUserId(int userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND));

        List<Address> addresses = addressRepository.findByUserId(userId);

        List<AddressResponseDto> addressDTOs = addresses.stream()
                .map(address -> {
                    AddressResponseDto dto = new AddressResponseDto();
                    dto.setAddressId(address.getId());
                    dto.setStreet(address.getStreet());
                    dto.setCity(address.getCity());
                    dto.setState(address.getState());
                    dto.setZipCode(address.getZipCode());
                    dto.setCountry(address.getCountry());
                    dto.setUserId(address.getUserId());
                    return dto;
                })
                .collect(Collectors.toList());

        return addressDTOs;
    }

    /**
     * Deletes all addresses associated with a specific user ID.
     *
     * @param userId the ID of the user whose addresses are to be deleted
     * @return a {@link CommonResponseDto} indicating the outcome of the delete operation
     */
    @Override
    public CommonResponseDto deleteAddressesByUserId(int userId) {
        int count = addressRepository.countByUserId(userId);
        if (count > 0) {
            addressRepository.deleteByUserId(userId);
        }
        return new CommonResponseDto(Constants.ADDRESS_DELETE_SUCCESSFULLY);
    }
}
