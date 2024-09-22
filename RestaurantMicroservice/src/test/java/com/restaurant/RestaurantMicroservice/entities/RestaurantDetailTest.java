package com.restaurant.RestaurantMicroservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link RestaurantDetail} entity.
 */
public class RestaurantDetailTest {

    /**
     * The {@link RestaurantDetail} instance used in tests.
     */
    private RestaurantDetail restaurantDetail;

    /**
     * A sample image data used for testing.
     */
    private byte[] sampleImage;

    /**
     * Sets up a {@link RestaurantDetail} instance and sample image data before each test.
     */
    @BeforeEach
    public void setup() {
        sampleImage = new byte[]{1, 2, 3};
        restaurantDetail = new RestaurantDetail(
                1,
                10,
                "Pasta Place",
                "123 Street",
                "1234567890",
                true,
                "9 AM - 9 PM",
                sampleImage,
                "Family-friendly Italian restaurant"
        );
    }

    /**
     * Tests the default constructor of the {@link RestaurantDetail} class.
     * It ensures that a {@link RestaurantDetail} created with the default constructor
     * has default values for its fields.
     */
    @Test
    public void testDefaultConstructor() {
        RestaurantDetail emptyRestaurantDetail = new RestaurantDetail();
        assertThat(emptyRestaurantDetail.getId()).isEqualTo(0);
        assertThat(emptyRestaurantDetail.getOwnerId()).isEqualTo(0);
        assertThat(emptyRestaurantDetail.getRestaurantName()).isNull();
        assertThat(emptyRestaurantDetail.getAddress()).isNull();
        assertThat(emptyRestaurantDetail.getContactNo()).isNull();
        assertThat(emptyRestaurantDetail.getOpen()).isNull();
        assertThat(emptyRestaurantDetail.getOpeningHours()).isNull();
        assertThat(emptyRestaurantDetail.getImageData()).isNull();
        assertThat(emptyRestaurantDetail.getDescription()).isNull();
    }

    /**
     * Tests the parameterized constructor of the {@link RestaurantDetail} class.
     * It ensures that a {@link RestaurantDetail} created with the parameterized constructor
     * has the correct values for its fields.
     */
    @Test
    public void testParameterizedConstructor() {
        assertThat(restaurantDetail.getId()).isEqualTo(1);
        assertThat(restaurantDetail.getOwnerId()).isEqualTo(10);
        assertThat(restaurantDetail.getRestaurantName()).isEqualTo("Pasta Place");
        assertThat(restaurantDetail.getAddress()).isEqualTo("123 Street");
        assertThat(restaurantDetail.getContactNo()).isEqualTo("1234567890");
        assertThat(restaurantDetail.getOpen()).isTrue();
        assertThat(restaurantDetail.getOpeningHours()).isEqualTo("9 AM - 9 PM");
        assertThat(restaurantDetail.getImageData()).isEqualTo(sampleImage);
        assertThat(restaurantDetail.getDescription()).isEqualTo("Family-friendly Italian restaurant");
    }

    /**
     * Tests the setter and getter methods of the {@link RestaurantDetail} class.
     * It verifies that the setter methods correctly set values,
     * and the getter methods return the expected values.
     */
    @Test
    public void testSettersAndGetters() {
        RestaurantDetail tempRestaurantDetail = new RestaurantDetail();
        byte[] newImage = new byte[]{5, 6, 7};

        tempRestaurantDetail.setId(2);
        tempRestaurantDetail.setOwnerId(20);
        tempRestaurantDetail.setRestaurantName("Burger Bistro");
        tempRestaurantDetail.setAddress("456 Avenue");
        tempRestaurantDetail.setContactNo("0987654321");
        tempRestaurantDetail.setOpen(false);
        tempRestaurantDetail.setOpeningHours("10 AM - 10 PM");
        tempRestaurantDetail.setImageData(newImage);
        tempRestaurantDetail.setDescription("Gourmet burger joint");

        assertThat(tempRestaurantDetail.getId()).isEqualTo(2);
        assertThat(tempRestaurantDetail.getOwnerId()).isEqualTo(20);
        assertThat(tempRestaurantDetail.getRestaurantName()).isEqualTo("Burger Bistro");
        assertThat(tempRestaurantDetail.getAddress()).isEqualTo("456 Avenue");
        assertThat(tempRestaurantDetail.getContactNo()).isEqualTo("0987654321");
        assertThat(tempRestaurantDetail.getOpen()).isFalse();
        assertThat(tempRestaurantDetail.getOpeningHours()).isEqualTo("10 AM - 10 PM");
        assertThat(tempRestaurantDetail.getImageData()).isEqualTo(newImage);
        assertThat(tempRestaurantDetail.getDescription()).isEqualTo("Gourmet burger joint");
    }

    /**
     * Tests the equality method of the {@link RestaurantDetail} class.
     * It verifies that a {@link RestaurantDetail} is equal to itself.
     */
    @Test
    public void testEqualsSameObject() {
        assertThat(restaurantDetail.equals(restaurantDetail)).isTrue();
    }

    /**
     * Tests the equality method of the {@link RestaurantDetail} class.
     * It verifies that two {@link RestaurantDetail} objects with the same values are considered equal.
     */
    @Test
    public void testEqualsDifferentObjectSameValues() {
        RestaurantDetail otherRestaurantDetail = new RestaurantDetail(
                1,
                10,
                "Pasta Place",
                "123 Street",
                "1234567890",
                true,
                "9 AM - 9 PM",
                sampleImage,
                "Family-friendly Italian restaurant"
        );
        assertThat(restaurantDetail).isEqualTo(otherRestaurantDetail);
    }

    /**
     * Tests the equality method of the {@link RestaurantDetail} class.
     * It verifies that two {@link RestaurantDetail} objects with different values are not considered equal.
     */
    @Test
    public void testEqualsDifferentObjectsDifferentValues() {
        RestaurantDetail otherRestaurantDetail = new RestaurantDetail(
                2,
                20,
                "Burger Bistro",
                "456 Avenue",
                "0987654321",
                false,
                "10 AM - 10 PM",
                new byte[]{5, 6, 7},
                "Gourmet burger joint"
        );
        assertThat(restaurantDetail).isNotEqualTo(otherRestaurantDetail);
    }

    /**
     * Tests the hashCode method of the {@link RestaurantDetail} class.
     * It ensures that two {@link RestaurantDetail} objects with the same values have the same hash code.
     */
    @Test
    public void testHashCodeSameObjectSameValues() {
        RestaurantDetail otherRestaurantDetail = new RestaurantDetail(
                1,
                10,
                "Pasta Place",
                "123 Street",
                "1234567890",
                true,
                "9 AM - 9 PM",
                sampleImage,
                "Family-friendly Italian restaurant"
        );
        assertThat(restaurantDetail.hashCode()).isEqualTo(otherRestaurantDetail.hashCode());
    }

    /**
     * Tests the hashCode method of the {@link RestaurantDetail} class.
     * It ensures that two {@link RestaurantDetail} objects with different values have different hash codes.
     */
    @Test
    public void testHashCodeDifferentObjectsDifferentValues() {
        RestaurantDetail otherRestaurantDetail = new RestaurantDetail(
                2,
                20,
                "Burger Bistro",
                "456 Avenue",
                "0987654321",
                false,
                "10 AM - 10 PM",
                new byte[]{5, 6, 7},
                "Gourmet burger joint"
        );
        assertThat(restaurantDetail.hashCode()).isNotEqualTo(otherRestaurantDetail.hashCode());
    }

    /**
     * Tests the setter method for image data in the {@link RestaurantDetail} class.
     * It verifies that the image data can be correctly set and retrieved.
     */
    @Test
    public void testSetImageData() {
        byte[] newImage = new byte[]{8, 9, 10};
        restaurantDetail.setImageData(newImage);
        assertThat(restaurantDetail.getImageData()).isEqualTo(newImage);
    }

    /**
     * Tests the setter method for opening hours in the {@link RestaurantDetail} class.
     * It verifies that the opening hours can be correctly set and retrieved.
     */
    @Test
    public void testSetOpeningHours() {
        restaurantDetail.setOpeningHours("8 AM - 8 PM");
        assertThat(restaurantDetail.getOpeningHours()).isEqualTo("8 AM - 8 PM");
    }

    /**
     * Tests the setter method for the open status in the {@link RestaurantDetail} class.
     * It verifies that the open status can be correctly set and retrieved.
     */
    @Test
    public void testSetOpen() {
        restaurantDetail.setOpen(false);
        assertThat(restaurantDetail.getOpen()).isFalse();
    }
}
