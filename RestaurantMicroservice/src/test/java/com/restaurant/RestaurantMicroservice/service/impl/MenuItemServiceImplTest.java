package com.restaurant.RestaurantMicroservice.service.impl;

import com.restaurant.RestaurantMicroservice.controller.MenuItemController;
import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateMenuItemRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.MenuItemUpdateRequestDto;
import com.restaurant.RestaurantMicroservice.entities.Category;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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


    @InjectMocks
    private MenuItemController menuItemController;

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

    @Test
    void testUpdateMenuItem_Success() {
        // Mock data setup
        int menuItemId = 1;
        MenuItemUpdateRequestDto menuItemUpdateRequestDto = new MenuItemUpdateRequestDto();
        menuItemUpdateRequestDto.setName("New Dish");
        menuItemUpdateRequestDto.setPrice(new BigDecimal("100.50"));
        menuItemUpdateRequestDto.setDescription("A delicious new dish");
        menuItemUpdateRequestDto.setCategoryId(2);

        MenuItems existingMenuItem = new MenuItems();
        existingMenuItem.setId(menuItemId);
        existingMenuItem.setFoodName("Old Dish");
        existingMenuItem.setRestaurantId(1); // Set a valid restaurant ID

        // Mock MultipartFile
        MockMultipartFile multipartFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "Test Image".getBytes());

        // Mock category repository behavior
        Category mockCategory = new Category(); // Assuming Category is your category entity
        when(categoryRepository.findById(menuItemUpdateRequestDto.getCategoryId())).thenReturn(Optional.of(mockCategory));

        // Mock menu item repository behavior
        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(existingMenuItem));
        when(menuItemRepository.save(any(MenuItems.class))).thenReturn(existingMenuItem);

        // Call the service method
        CommonResponseDto responseDto = menuItemService.updateMenuItem(menuItemId, menuItemUpdateRequestDto, multipartFile);

        // Assertions
        assertNotNull(responseDto);
        assertEquals("Updated Successfully", responseDto.getMessage());
        verify(menuItemRepository, times(1)).findById(menuItemId);
        verify(menuItemRepository, times(1)).save(any(MenuItems.class));
        verify(categoryRepository, times(1)).findById(menuItemUpdateRequestDto.getCategoryId()); // Verify category check
    }

    @Test
    void testUpdateMenuItem_MenuItemNotFound() {
        // Mock data setup
        int menuItemId = 1;
        MenuItemUpdateRequestDto menuItemUpdateRequestDto = new MenuItemUpdateRequestDto();
        menuItemUpdateRequestDto.setName("New Dish");
        menuItemUpdateRequestDto.setPrice(new BigDecimal("100.50"));
        menuItemUpdateRequestDto.setDescription("A delicious new dish");
        menuItemUpdateRequestDto.setCategoryId(2);

        // Mock MultipartFile
        MockMultipartFile multipartFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "Test Image".getBytes());

        // Mock repository behavior
        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.empty());

        // Call the service method and assert exception
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            menuItemService.updateMenuItem(menuItemId, menuItemUpdateRequestDto, multipartFile);
        });

        assertEquals("Menu Item with this ID was not found", exception.getMessage());
        verify(menuItemRepository, times(1)).findById(menuItemId);
        verify(menuItemRepository, never()).save(any(MenuItems.class));
    }
   /* @Test
    void testUpdateMenuItem_WithoutImage() {
        // Given
        int existingMenuItemId = 1; // Sample existing menu item ID
        MenuItemUpdateRequestDto updateRequestDto = new MenuItemUpdateRequestDto();
        updateRequestDto.setPrice(BigDecimal.valueOf(50.0)); // Valid price
        updateRequestDto.setName("Updated Menu Item");

        // Mocking the service method
        when(menuItemService.updateMenuItem(eq(existingMenuItemId), any(MenuItemUpdateRequestDto.class), isNull()))
                .thenReturn(new CommonResponseDto("Menu item updated successfully"));

        // When
        ResponseEntity<CommonResponseDto> response = menuItemController.updateMenuItem(existingMenuItemId, updateRequestDto, null);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Menu item updated successfully", response.getBody().getMessage());

        // Verify the interaction with the service
        verify(menuItemService).updateMenuItem(existingMenuItemId, updateRequestDto, null);
    }
*/


}
