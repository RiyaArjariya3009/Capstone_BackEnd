package com.restaurant.userservice.controller;

import com.restaurant.userservice.dto.in.AddressRequestDto;
import com.restaurant.userservice.dto.in.AddressUpdateRequestDto;
import com.restaurant.userservice.dto.out.AddressResponseDto;
import com.restaurant.userservice.dto.out.CommonResponseDto;
import com.restaurant.userservice.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

/**
 * Test class for {@link AddressController}.
 *
 * This class contains tests for the CRUD operations provided by the {@link AddressController}.
 * It uses MockMvc to perform HTTP requests and verify the responses for adding, retrieving,
 * updating, and deleting addresses, as well as for retrieving all addresses for a specific user.
 */
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    /**
     * MockMvc instance used for performing HTTP requests and verifying the responses.
     * It allows testing the web layer by simulating HTTP requests and asserting the results
     * without starting a full server.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Mocked instance of {@link AddressService}.
     * It is used to simulate the behavior of the real AddressService in unit tests.
     * By mocking this service, we can specify the expected behavior and responses
     * to validate how the controller handles different scenarios.
     */
    @MockBean
    private AddressService addressService;

    /**
     * DTO used for creating a new address.
     */
    private AddressRequestDto addressRequestDto;

    /**
     * DTO representing an address returned in response.
     */
    private AddressResponseDto addressResponseDto;

    /**
     * DTO used for updating an existing address.
     */
    private AddressUpdateRequestDto addressUpdateRequestDto;

    /**
     * DTO representing a common response message.
     */
    private CommonResponseDto commonResponseDto;

    /**
     * Set up test data before each test case.
     */
    @BeforeEach
    public void setUp() {
        addressRequestDto = new AddressRequestDto(
                "123 Main St",
                "Cityville",
                "Stateville",
                "12345",
                "Countryland"
        );
        addressResponseDto = new AddressResponseDto(
                1,
                1,
                "123 Main St",
                "Cityville",
                "Stateville",
                "12345",
                "Countryland"
        );
        addressUpdateRequestDto = new AddressUpdateRequestDto();
        addressUpdateRequestDto.setStreet("456 Elm St");
        addressUpdateRequestDto.setCity("New City");
        addressUpdateRequestDto.setState("New State");
        addressUpdateRequestDto.setZipCode("67890");
        addressUpdateRequestDto.setCountry("New Country");
        commonResponseDto = new CommonResponseDto("Success");
    }

    /**
     * Test for adding a new address.
     *
     * Verifies that the address is successfully added and the response matches the expected values.
     *
     * @throws Exception if there is an issue performing the request.
     */
    @Test
    public void testAddAddress() throws Exception {
        when(addressService.addAddress(any(AddressRequestDto.class)))
                .thenReturn(addressResponseDto);

        mockMvc.perform(post("/api/address/addAddresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"street\": \"123 Main St\", "
                                + "\"city\": \"Cityville\", "
                                + "\"state\": \"Stateville\", "
                                + "\"zipCode\": \"12345\", "
                                + "\"country\": \"Countryland\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.street").value("123 Main St"))
                .andExpect(jsonPath("$.city").value("Cityville"))
                .andExpect(jsonPath("$.state").value("Stateville"))
                .andExpect(jsonPath("$.zipCode").value("12345"))
                .andExpect(jsonPath("$.country").value("Countryland"));
    }

    /**
     * Test for retrieving an address by its ID.
     *
     * Verifies that the address details are correctly retrieved and match the expected values.
     *
     * @throws Exception if there is an issue performing the request.
     */
    @Test
    public void testGetAddressById() throws Exception {
        when(addressService.getAddressById(1))
                .thenReturn(addressResponseDto);

        mockMvc.perform(get("/api/address/getAddressById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.street").value("123 Main St"))
                .andExpect(jsonPath("$.city").value("Cityville"))
                .andExpect(jsonPath("$.state").value("Stateville"))
                .andExpect(jsonPath("$.zipCode").value("12345"))
                .andExpect(jsonPath("$.country").value("Countryland"));
    }

    /**
     * Test for updating an existing address.
     *
     * Verifies that the address is successfully updated and the response message is as expected.
     *
     * @throws Exception if there is an issue performing the request.
     */
    @Test
    public void testUpdateAddress() throws Exception {
        when(addressService.updateAddress(any(Integer.class), any(AddressUpdateRequestDto.class)))
                .thenReturn(commonResponseDto);

        mockMvc.perform(put("/api/address/updateAddressById/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"street\": \"456 Elm St\", "
                                + "\"city\": \"New City\", "
                                + "\"state\": \"New State\", "
                                + "\"zipCode\": \"67890\", "
                                + "\"country\": \"New Country\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"));
    }

    /**
     * Test for deleting an address by its ID.
     *
     * Verifies that the address is successfully deleted and the response message is as expected.
     *
     * @throws Exception if there is an issue performing the request.
     */
    @Test
    public void testDeleteAddress() throws Exception {
        when(addressService.deleteAddress(1))
                .thenReturn(commonResponseDto);

        mockMvc.perform(delete("/api/address/deleteAddressById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"));
    }

    /**
     * Test for retrieving all addresses associated with a specific user ID.
     *
     * Verifies that all addresses for the given user ID are correctly retrieved and match the expected values.
     *
     * @throws Exception if there is an issue performing the request.
     */
    @Test
    public void testGetAllAddressesByUserId() throws Exception {
        List<AddressResponseDto> addressList = Arrays.asList(addressResponseDto);
        when(addressService.getAllAddressesByUserId(1))
                .thenReturn(addressList);

        mockMvc.perform(get("/api/address/getAddressesByUserId/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].street").value("123 Main St"))
                .andExpect(jsonPath("$[0].city").value("Cityville"))
                .andExpect(jsonPath("$[0].state").value("Stateville"))
                .andExpect(jsonPath("$[0].zipCode").value("12345"))
                .andExpect(jsonPath("$[0].country").value("Countryland"));
    }
}
