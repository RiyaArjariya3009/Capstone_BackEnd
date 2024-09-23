package com.restaurant.RestaurantMicroservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link MenuItems} entity class.
 * This class tests the default constructor, parameterized constructor,
 * getters, setters, equals, hashCode, and other related methods.
 */
public class MenuItemsTest {

    /**
     * Creating variable for menuItems.
     */


     private MenuItems menuItem;
     /**
     * Creating sample image.
     */
      private byte[] sampleImage;

    /**
     * Sets up test data before each test.
     * Initializes a sample byte array representing an image and creates a sample {@link MenuItems} object.
     */
    @BeforeEach
    public void setup() {
        sampleImage = new byte[]{1, 2, 3}; // A sample byte array representing an image.
        menuItem = new MenuItems(1, 100, 200, "Pizza", "Delicious cheese pizza", true, BigDecimal.valueOf(12.99), sampleImage);
    }

    /**
     * Tests the default constructor of {@link MenuItems}.
     * Ensures that all fields are initialized to their default values.
     */
    @Test
    public void testDefaultConstructor() {
        MenuItems emptyMenuItem = new MenuItems();
        assertThat(emptyMenuItem.getId()).isEqualTo(0);
        assertThat(emptyMenuItem.getRestaurantId()).isEqualTo(0);
        assertThat(emptyMenuItem.getCategoryId()).isEqualTo(0);
        assertThat(emptyMenuItem.getFoodName()).isNull();
        assertThat(emptyMenuItem.getDescription()).isNull();
        assertThat(emptyMenuItem.getAvailable()).isNull();
        assertThat(emptyMenuItem.getPrice()).isNull();
        assertThat(emptyMenuItem.getImageData()).isNull();
    }

    /**
     * Tests the parameterized constructor of {@link MenuItems}.
     * Ensures that the fields are properly initialized with the provided values.
     */
    @Test
    public void testParameterizedConstructor() {
        assertThat(menuItem.getId()).isEqualTo(1);
        assertThat(menuItem.getRestaurantId()).isEqualTo(100);
        assertThat(menuItem.getCategoryId()).isEqualTo(200);
        assertThat(menuItem.getFoodName()).isEqualTo("Pizza");
        assertThat(menuItem.getDescription()).isEqualTo("Delicious cheese pizza");
        assertThat(menuItem.getAvailable()).isTrue();
        assertThat(menuItem.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(12.99));
        assertThat(menuItem.getImageData()).isEqualTo(sampleImage);
    }

    /**
     * Tests the setter and getter methods of {@link MenuItems}.
     * Ensures that values set via the setters are correctly retrieved using the getters.
     */
    @Test
    public void testSettersAndGetters() {
        MenuItems localMenuItem = new MenuItems(); // Rename the local variable to 'localMenuItem'
        byte[] newImage = new byte[]{5, 6, 7};

        localMenuItem.setId(2);
        localMenuItem.setRestaurantId(150);
        localMenuItem.setCategoryId(250);
        localMenuItem.setFoodName("Burger");
        localMenuItem.setDescription("Juicy beef burger");
        localMenuItem.setAvailable(false);
        localMenuItem.setPrice(BigDecimal.valueOf(8.99));
        localMenuItem.setImageData(newImage);

        assertThat(localMenuItem.getId()).isEqualTo(2);
        assertThat(localMenuItem.getRestaurantId()).isEqualTo(150);
        assertThat(localMenuItem.getCategoryId()).isEqualTo(250);
        assertThat(localMenuItem.getFoodName()).isEqualTo("Burger");
        assertThat(localMenuItem.getDescription()).isEqualTo("Juicy beef burger");
        assertThat(localMenuItem.getAvailable()).isFalse();
        assertThat(localMenuItem.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(8.99));
        assertThat(localMenuItem.getImageData()).isEqualTo(newImage);
    }

    /**
     * Tests that the {@link MenuItems#equals(Object)} method returns true when comparing the same object.
     */
    @Test
    public void testEqualsSameObject() {
        assertThat(menuItem.equals(menuItem)).isTrue();
    }

    /**
     * Tests the {@link MenuItems#equals(Object)} method with different objects but same values.
     * Ensures that objects with the same values are considered equal.
     */
    @Test
    public void testEqualsDifferentObjectSameValues() {
        MenuItems menuItem2 = new MenuItems(1, 100, 200, "Pizza", "Delicious cheese pizza", true, BigDecimal.valueOf(12.99),
                sampleImage);
        assertThat(menuItem).isEqualTo(menuItem2);
    }

    /**
     * Tests the {@link MenuItems#equals(Object)} method with different objects and different values.
     * Ensures that objects with different values are not considered equal.
     */
    @Test
    public void testEqualsDifferentObjectsDifferentValues() {
        MenuItems menuItem2 = new MenuItems(2, 101, 201, "Pasta",
                "Creamy pasta", false, BigDecimal.valueOf(9.99), new byte[]{4, 5, 6});
        assertThat(menuItem).isNotEqualTo(menuItem2);
    }

    /**
     * Tests the {@link MenuItems#hashCode()} method for objects with the same values.
     * Ensures that objects with the same values have the same hashCode.
     */
    @Test
    public void testHashCodeSameObjectSameValues() {
        MenuItems menuItem2 = new MenuItems(1, 100, 200, "Pizza", "Delicious cheese pizza", true,
                BigDecimal.valueOf(12.99), sampleImage);
        assertThat(menuItem.hashCode()).isEqualTo(menuItem2.hashCode());
    }

    /**
     * Tests the {@link MenuItems#hashCode()} method for objects with different values.
     * Ensures that objects with different values have different hash codes.
     */
    @Test
    public void testHashCodeDifferentObjectsDifferentValues() {
        MenuItems menuItem2 = new MenuItems(2, 101, 201, "Pasta", "Creamy pasta", false, BigDecimal.valueOf(9.99),
                new byte[]{4, 5, 6});
        assertThat(menuItem.hashCode()).isNotEqualTo(menuItem2.hashCode());
    }

    /**
     * Tests the {@link MenuItems#setImageData(byte[])} method.
     * Ensures that the image data is correctly updated.
     */
    @Test
    public void testSetImageData() {
        byte[] newImage = new byte[]{10, 11, 12};
        menuItem.setImageData(newImage);
        assertThat(menuItem.getImageData()).isEqualTo(newImage);
    }

    /**
     * Tests the {@link MenuItems#getPrice()} method.
     * Ensures that the correct price is returned.
     */
    @Test
    public void testGetPrice() {
        assertThat(menuItem.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(12.99));
    }

    /**
     * Tests the {@link MenuItems#setPrice(BigDecimal)} method.
     * Ensures that the price is correctly updated.
     */
    @Test
    public void testSetPrice() {
        menuItem.setPrice(BigDecimal.valueOf(14.99));
        assertThat(menuItem.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(14.99));
    }
}
