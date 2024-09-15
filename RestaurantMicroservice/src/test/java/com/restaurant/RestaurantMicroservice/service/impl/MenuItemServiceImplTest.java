package com.restaurant.RestaurantMicroservice.service.impl;

import com.restaurant.RestaurantMicroservice.dtos.CreateMenuItemRequestDto;
import com.restaurant.RestaurantMicroservice.entities.MenuItems;
import com.restaurant.RestaurantMicroservice.exception.NotFoundException;
import com.restaurant.RestaurantMicroservice.repository.CategoryRepository;
import com.restaurant.RestaurantMicroservice.repository.MenuItemRepository;
import com.restaurant.RestaurantMicroservice.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link MenuItemServiceImpl} class.
 */
public class MenuItemServiceImplTest {

    /**
     * Mocked {@link CategoryRepository} instance.
     */
    @Mock
    private CategoryRepository categoryRepository;

    /**
     * Mocked {@link RestaurantRepository} instance.
     */
    @Mock
    private RestaurantRepository restaurantRepository;

    /**
     * Mocked {@link MenuItemRepository} instance.
     */
    @Mock
    private MenuItemRepository menuItemRepository;

    /**
     * {@link MenuItemServiceImpl} instance under test.
     */
    @InjectMocks
    private MenuItemServiceImpl menuItemService;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the case where a restaurant is not found when creating a menu item.
     * <p>
     * This test verifies that a {@link NotFoundException} is thrown when the
     * restaurant with the specified ID is not found in the repository.
     * </p>
     */
    @Test
    public void testCreateMenuItemRestaurantNotFound() {
        CreateMenuItemRequestDto dto = new CreateMenuItemRequestDto();
        dto.setRestaurantId(1);
        dto.setCategoryId(1);
        dto.setFoodName("Pizza");
        dto.setDescription("Delicious pizza");
        dto.setPrice(BigDecimal.valueOf(10.0));

        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> menuItemService.createMenuItem(dto, null));
    }

    /**
     * Tests the successful deletion of a menu item.
     * <p>
     * This test verifies that the menu item is deleted from the repository
     * when it exists, and the delete operation is called exactly once.
     * </p>
     */
    @Test
    public void testDeleteMenuItemSuccess() {
        MenuItems menuItem = new MenuItems();
        when(menuItemRepository.findById(anyInt())).thenReturn(Optional.of(menuItem));

        menuItemService.deleteMenuItem(1);

        verify(menuItemRepository).delete(menuItem);
    }

    /**
     * Tests the case where a menu item is not found when attempting to delete.
     * <p>
     * This test verifies that a {@link NotFoundException} is thrown when
     * the menu item with the specified ID is not found in the repository.
     * </p>
     */
    @Test
    public void testDeleteMenuItemNotFound() {
        when(menuItemRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> menuItemService.deleteMenuItem(1));
    }

    // Add more test cases as needed for other methods.
}
