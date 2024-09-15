package com.restaurant.RestaurantMicroservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link Category} entity.
 */
public class CategoryTest {

    /**
     * The {@link Category} instance used in tests.
     */
    private Category categoryInstance;

    /**
     * Sets up a {@link Category} instance before each test.
     */
    @BeforeEach
    public void setup() {
        categoryInstance = new Category("Italian", 101);
    }

    /**
     * Tests the default constructor of the {@link Category} class.
     * It ensures that a category created with the default constructor
     * has default values for its fields.
     */
    @Test
    public void testDefaultConstructor() {
        Category emptyCategory = new Category();
        assertThat(emptyCategory.getId()).isEqualTo(0);
        assertThat(emptyCategory.getName()).isNull();
        assertThat(emptyCategory.getRestaurantId()).isEqualTo(0);
    }

    /**
     * Tests the parameterized constructor of the {@link Category} class.
     * It ensures that a category created with the parameterized constructor
     * has the correct values for its fields.
     */
    @Test
    public void testParameterizedConstructor() {
        Category categoryWithParams = new Category("Italian", 101);
        assertThat(categoryWithParams.getName()).isEqualTo("Italian");
        assertThat(categoryWithParams.getRestaurantId()).isEqualTo(101);
    }

    /**
     * Tests the setter and getter methods of the {@link Category} class.
     * It verifies that the setter methods correctly set values,
     * and the getter methods return the expected values.
     */
    @Test
    public void testSettersAndGetters() {
        Category tempCategory = new Category();
        tempCategory.setId(1);
        tempCategory.setName("Mexican");
        tempCategory.setRestaurantId(102);

        assertThat(tempCategory.getId()).isEqualTo(1);
        assertThat(tempCategory.getName()).isEqualTo("Mexican");
        assertThat(tempCategory.getRestaurantId()).isEqualTo(102);
    }

    /**
     * Tests the equality method of the {@link Category} class.
     * It verifies that a category is equal to itself.
     */
    @Test
    public void testEqualsSameObject() {
        Category sameCategory = new Category("Chinese", 103);
        assertThat(sameCategory.equals(sameCategory)).isTrue();
    }

    /**
     * Tests the equality method of the {@link Category} class.
     * It verifies that two categories with the same values are considered equal.
     */
    @Test
    public void testEqualsDifferentObjectSameValues() {
        Category category1 = new Category("Chinese", 103);
        Category category2 = new Category("Chinese", 103);
        category1.setId(1);
        category2.setId(1);

        assertThat(category1).isEqualTo(category2);
    }

    /**
     * Tests the equality method of the {@link Category} class.
     * It verifies that two categories with different values are not considered equal.
     */
    @Test
    public void testEqualsDifferentObjectsDifferentValues() {
        Category category1 = new Category("Italian", 101);
        category1.setId(1);

        Category category2 = new Category("Mexican", 102);
        category2.setId(2);

        assertThat(category1).isNotEqualTo(category2);
    }

    /**
     * Tests the hashCode method of the {@link Category} class.
     * It ensures that two categories with the same values have the same hash code.
     */
    @Test
    public void testHashCodeSameObjectSameValues() {
        Category category1 = new Category("Italian", 101);
        category1.setId(1);

        Category category2 = new Category("Italian", 101);
        category2.setId(1);

        assertThat(category1.hashCode()).isEqualTo(category2.hashCode());
    }

    /**
     * Tests the hashCode method of the {@link Category} class.
     * It ensures that two categories with different values have different hash codes.
     */
    @Test
    public void testHashCodeDifferentObjectsDifferentValues() {
        Category category1 = new Category("Italian", 101);
        category1.setId(1);

        Category category2 = new Category("Mexican", 102);
        category2.setId(2);

        assertThat(category1.hashCode()).isNotEqualTo(category2.hashCode());
    }
}
