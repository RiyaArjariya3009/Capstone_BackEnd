package com.restaurant.userservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit test class for {@link Address}.
 *
 * This class contains tests for the {@link Address} entity, verifying its constructors,
 * getters, setters, and default behavior. It uses JUnit 5 for testing.
 */
public class AddressTest {

    /**
     * The {@link Address} instance used in the test methods.
     * This is initialized in the {@link #setUp()} method before each test case.
     */
    private Address testAddress;

    /**
     * Sets up the test environment before each test case.
     * Initializes a sample {@link Address} object with predefined values.
     */
    @BeforeEach
    public void setUp() {
        testAddress = new Address(1, "123 Main St", "Springfield", "IL", "62701", "USA");
    }

    /**
     * Tests the constructor of the {@link Address} class.
     * Verifies that the constructor correctly initializes the address properties.
     */
    @Test
    public void testAddressConstructor() {
        Address address = new Address(1, "123 Main St", "Springfield", "IL", "62701", "USA");
        assertEquals(1, address.getUserId(), "User ID should be set correctly");
        assertEquals("123 Main St", address.getStreet(), "Street should be set correctly");
        assertEquals("Springfield", address.getCity(), "City should be set correctly");
        assertEquals("IL", address.getState(), "State should be set correctly");
        assertEquals("62701", address.getZipCode(), "Zip Code should be set correctly");
        assertEquals("USA", address.getCountry(), "Country should be set correctly");
    }

    /**
     * Tests the getters and setters of the {@link Address} class.
     * Verifies that the setter methods correctly set the values and
     * the getter methods return the expected values.
     */
    @Test
    public void testGettersAndSetters() {
        testAddress.setId(1);
        assertEquals(1, testAddress.getId(), "ID should be set correctly");

        testAddress.setUserId(2);
        assertEquals(2, testAddress.getUserId(), "User ID should be set correctly");

        testAddress.setStreet("456 Elm St");
        assertEquals("456 Elm St", testAddress.getStreet(), "Street should be set correctly");

        testAddress.setCity("Metropolis");
        assertEquals("Metropolis", testAddress.getCity(), "City should be set correctly");

        testAddress.setState("NY");
        assertEquals("NY", testAddress.getState(), "State should be set correctly");

        testAddress.setZipCode("10001");
        assertEquals("10001", testAddress.getZipCode(), "Zip Code should be set correctly");

        testAddress.setCountry("Canada");
        assertEquals("Canada", testAddress.getCountry(), "Country should be set correctly");
    }

    /**
     * Tests the default constructor of the {@link Address} class.
     * Verifies that the default constructor initializes the address properties
     * to their default values.
     */
    @Test
    public void testDefaultConstructor() {
        Address defaultAddress = new Address();
        assertNull(defaultAddress.getStreet(), "Street should be null");
        assertNull(defaultAddress.getCity(), "City should be null");
        assertNull(defaultAddress.getState(), "State should be null");
        assertNull(defaultAddress.getZipCode(), "Zip Code should be null");
        assertNull(defaultAddress.getCountry(), "Country should be null");
        assertEquals(0, defaultAddress.getUserId(), "User ID should be 0");
    }
}
