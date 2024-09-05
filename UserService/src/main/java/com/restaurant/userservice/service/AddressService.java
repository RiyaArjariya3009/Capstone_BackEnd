package com.restaurant.userservice.service;

import com.restaurant.userservice.dto.in.AddressRequestDto;
import com.restaurant.userservice.dto.in.AddressUpdateRequestDto;
import com.restaurant.userservice.dto.out.AddressResponseDto;
import com.restaurant.userservice.dto.out.CommonResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing {@link com.restaurant.userservice.entities.Address} entities.
 */
@Service
public interface AddressService {

    /**
     * Adds a new address.
     *
     * @param addressAddDTO the DTO containing the details of the address to be added
     * @return the {@link AddressResponseDto} containing the details of the added address
     */
    AddressResponseDto addAddress(AddressRequestDto addressAddDTO);

    /**
     * Updates an existing address.
     *
     * @param addressId the ID of the address to be updated
     * @param addressUpdateDTO the DTO containing the updated details of the address
     * @return a {@link CommonResponseDto} indicating the outcome of the update operation
     */
    CommonResponseDto updateAddress(int addressId, AddressUpdateRequestDto addressUpdateDTO);

    /**
     * Deletes an address by its ID.
     *
     * @param addressId the ID of the address to be deleted
     * @return a {@link CommonResponseDto} indicating the outcome of the delete operation
     */
    CommonResponseDto deleteAddress(int addressId);

    /**
     * Retrieves all addresses associated with a specific user ID.
     *
     * @param userId the ID of the user whose addresses are to be retrieved
     * @return a list of {@link AddressResponseDto} representing the addresses of the user
     */
    List<AddressResponseDto> getAllAddressesByUserId(int userId);

    /**
     * Deletes all addresses associated with a specific user ID.
     *
     * @param userId the ID of the user whose addresses are to be deleted
     * @return a {@link CommonResponseDto} indicating the outcome of the delete operation
     */
    CommonResponseDto deleteAddressesByUserId(int userId);

    /**
     * Retrieves an address by its ID.
     *
     * @param addressId the ID of the address to be retrieved
     * @return the {@link AddressResponseDto} containing the details of the address
     */
    AddressResponseDto getAddressById(int addressId);
}
