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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for the AddressServiceImpl class.
 * This class contains unit tests for various methods in AddressServiceImpl.
 */
public class AddressServiceImplTest {

    /**
     * The service class under test, AddressServiceImpl.
     * It is injected with mock dependencies to test its methods in isolation.
     */
    @InjectMocks
    private AddressServiceImpl addressService;

    /**
     * Mock of the UserRepository, used to simulate database operations related to User entities.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * Mock of the AddressRepository, used to simulate database operations related to Address entities.
     */
    @Mock
    private AddressRepository addressRepository;

    /**
     * Initializes the mocks before each test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the addAddress method to ensure a valid address is added successfully.
     */
    @Test
    public void testAddAddress() {
        AddressRequestDto requestDto = new AddressRequestDto();
        // Populate requestDto with test data
        // e.g., requestDto.setUserId(1);
        // e.g., requestDto.setStreet("123 Main St");

        User user = new User();
        user.setId(1);
        user.setRole(RoleType.CUSTOMER);

        Address address = new Address();
        address.setId(1);
        address.setUserId(user.getId());
        // Set other address fields

        AddressResponseDto responseDto = new AddressResponseDto();
        // Populate responseDto with expected test data
        // e.g., responseDto.setStreet("123 Main St");

        when(userRepository.findById(requestDto.getId())).thenReturn(Optional.of(user));
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        AddressResponseDto result = addressService.addAddress(requestDto);

        assertNotNull(result);
        assertEquals(responseDto.getStreet(), result.getStreet());
        verify(userRepository, times(1)).findById(requestDto.getId());
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    /**
     * Tests the addAddress method to ensure it throws UserNotFoundException when the user is not found.
     */
    @Test
    public void testAddAddressUserNotFound() {
        AddressRequestDto requestDto = new AddressRequestDto();
        // Populate requestDto with test data
        // e.g., requestDto.setUserId(1);

        when(userRepository.findById(requestDto.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> addressService.addAddress(requestDto));
    }

    /**
     * Tests the addAddress method to ensure it throws UserNotAllowedExceptions when the user role is not allowed
     * to add an address.
     */
    @Test
    public void testAddAddressUserNotAllowed() {
        AddressRequestDto requestDto = new AddressRequestDto();
        // Populate requestDto with test data
        // e.g., requestDto.setUserId(1);

        User user = new User();
        user.setId(1);
        user.setRole(RoleType.RESTAURANT_OWNER); // Not a CUSTOMER

        when(userRepository.findById(requestDto.getId())).thenReturn(Optional.of(user));

        assertThrows(UserNotAllowedExceptions.class, () -> addressService.addAddress(requestDto));
    }

    /**
     * Tests the getAddressById method to ensure it returns the correct address.
     */
    @Test
    public void testGetAddressById() {
        int addressId = 1;
        Address address = new Address();
        address.setId(addressId);
        // Set other address fields

        AddressResponseDto responseDto = new AddressResponseDto();
        // Populate responseDto with expected test data
        // e.g., responseDto.setStreet("123 Main St");

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        AddressResponseDto result = addressService.getAddressById(addressId);

        assertNotNull(result);
        assertEquals(responseDto.getStreet(), result.getStreet());
        verify(addressRepository, times(1)).findById(addressId);
    }

    /**
     * Tests the getAddressById method to ensure it throws AddressNotFoundExceptions when the address is not found.
     */
    @Test
    public void testGetAddressByIdNotFound() {
        int addressId = 1;

        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundExceptions.class, () -> addressService.getAddressById(addressId));
    }

    /**
     * Tests the updateAddress method to ensure it updates the address successfully.
     */
    @Test
    public void testUpdateAddress() {
        int addressId = 1;
        AddressUpdateRequestDto updateDto = new AddressUpdateRequestDto();
        // Populate updateDto with test data
        // e.g., updateDto.setStreet("456 Elm St");

        Address address = new Address();
        address.setId(addressId);
        address.setUserId(1);
        // Set other address fields

        User user = new User();
        user.setId(1);
        user.setRole(RoleType.CUSTOMER);

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));
        when(userRepository.findById(address.getUserId())).thenReturn(Optional.of(user));

        CommonResponseDto result = addressService.updateAddress(addressId, updateDto);

        assertNotNull(result);
        assertEquals(Constants.ADDRESS_UPDATE_SUCCESSFULLY, result.getMessage());
        verify(addressRepository, times(1)).save(address);
    }

    /**
     * Tests the updateAddress method to ensure it throws AddressNotFoundExceptions when the address is not found.
     */
    @Test
    public void testUpdateAddressUserNotFound() {
        int addressId = 1;
        AddressUpdateRequestDto updateDto = new AddressUpdateRequestDto();

        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundExceptions.class, () -> addressService.updateAddress(addressId, updateDto));
    }

    /**
     * Tests the updateAddress
     * method to ensure it throws UserNotAllowedExceptions when the user role is not allowed to update the address.
     */
    @Test
    public void testUpdateAddressUserNotAllowed() {
        int addressId = 1;
        AddressUpdateRequestDto updateDto = new AddressUpdateRequestDto();

        Address address = new Address();
        address.setId(addressId);
        address.setUserId(1);

        User user = new User();
        user.setId(1);
        user.setRole(RoleType.RESTAURANT_OWNER); // Not a CUSTOMER

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));
        when(userRepository.findById(address.getUserId())).thenReturn(Optional.of(user));

        assertThrows(UserNotAllowedExceptions.class, () -> addressService.updateAddress(addressId, updateDto));
    }

    /**
     * Tests the deleteAddress method to ensure it deletes the address successfully.
     */
    @Test
    public void testDeleteAddress() {
        int addressId = 1;
        Address address = new Address();
        address.setId(addressId);

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        CommonResponseDto result = addressService.deleteAddress(addressId);

        assertNotNull(result);
        assertEquals(Constants.ADDRESS_DELETE_SUCCESSFULLY, result.getMessage());
        verify(addressRepository, times(1)).delete(address);
    }

    /**
     * Tests the deleteAddress method to ensure it throws AddressNotFoundExceptions when the address is not found.
     */
    @Test
    public void testDeleteAddressNotFound() {
        int addressId = 1;

        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundExceptions.class, () -> addressService.deleteAddress(addressId));
    }

    /**
     * Tests the getAllAddressesByUserId method to ensure it returns all addresses for a user.
     */
    @Test
    public void testGetAllAddressesByUserId() {
        int userId = 1;
        Address address = new Address();
        address.setUserId(userId);
        // Set other address fields

        AddressResponseDto dto = new AddressResponseDto();
        dto.setUserId(userId);
        // Populate dto with expected data

        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(addressRepository.findByUserId(userId)).thenReturn(Arrays.asList(address));

        List<AddressResponseDto> result = addressService.getAllAddressesByUserId(userId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(dto.getUserId(), result.get(0).getUserId());
    }

    /**
     * Tests the deleteAddressesByUserId method to ensure it deletes all addresses for a user.
     */
    @Test
    public void testDeleteAddressesByUserId() {
        int userId = 1;

        when(addressRepository.countByUserId(userId)).thenReturn(1);

        CommonResponseDto result = addressService.deleteAddressesByUserId(userId);

        assertNotNull(result);
        assertEquals(Constants.ADDRESS_DELETE_SUCCESSFULLY, result.getMessage());
        verify(addressRepository, times(1)).deleteByUserId(userId);
    }


}
