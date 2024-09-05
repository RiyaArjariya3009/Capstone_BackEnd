package com.restaurant.userservice.controller;

import com.restaurant.userservice.dto.in.AddressRequestDto;
import com.restaurant.userservice.dto.in.AddressUpdateRequestDto;
import com.restaurant.userservice.dto.out.AddressResponseDto;
import com.restaurant.userservice.dto.out.CommonResponseDto;
import com.restaurant.userservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * AddressController handles HTTP requests related to address management.
 * It provides endpoints for adding, retrieving, updating, and deleting addresses.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/address")
public class AddressController {
    /**
     * Service to manage address-related operations.
     */
    @Autowired
    private AddressService addressService;

    /**
     * Adds a new address for a user.
     *
     * @param addressAddDTO The DTO containing the address information.
     * @return A ResponseEntity containing the created address details and HTTP status CREATED.
     */
    @PostMapping("/addAddresses")
    public ResponseEntity<AddressResponseDto> addAddress(
            @Valid @RequestBody AddressRequestDto addressAddDTO) {

        AddressResponseDto responseDTO = addressService.addAddress(addressAddDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Retrieves an address by its ID.
     *
     * @param addressId The ID of the address to be retrieved.
     * @return A ResponseEntity containing the address details and HTTP status OK.
     */
    @GetMapping("getAddressById/{addressId}")
    public ResponseEntity<AddressResponseDto> getAddressById(@PathVariable int addressId) {
        AddressResponseDto responseDTO = addressService.getAddressById(addressId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Updates an address by its ID.
     *
     * @param addressId The ID of the address to be updated.
     * @param addressUpdateDTO The DTO containing the updated address information.
     * @return A ResponseEntity containing a common response DTO and HTTP status OK.
     */
    @PutMapping("updateAddressById/{addressId}")
    public ResponseEntity<CommonResponseDto> updateAddress(
            @PathVariable int addressId,
            @Valid @RequestBody AddressUpdateRequestDto addressUpdateDTO) {

        CommonResponseDto responseDTO = addressService.updateAddress(addressId, addressUpdateDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Deletes an address by its ID.
     *
     * @param addressId The ID of the address to be deleted.
     * @return A ResponseEntity containing a common response DTO and HTTP status OK.
     */
    @DeleteMapping("deleteAddressById/{addressId}")
    public ResponseEntity<CommonResponseDto> deleteAddress(@PathVariable int addressId) {
        CommonResponseDto responseDTO = addressService.deleteAddress(addressId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Retrieves all addresses associated with a specific user ID.
     *
     * @param userId The ID of the user whose addresses are to be retrieved.
     * @return A ResponseEntity containing a list of address response DTOs and HTTP status OK.
     */
    @GetMapping("getAddressesByUserId/{userId}")
    public ResponseEntity<List<AddressResponseDto>> getAllAddresses(@PathVariable int userId) {
        List<AddressResponseDto> responseDTO = addressService.getAllAddressesByUserId(userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
