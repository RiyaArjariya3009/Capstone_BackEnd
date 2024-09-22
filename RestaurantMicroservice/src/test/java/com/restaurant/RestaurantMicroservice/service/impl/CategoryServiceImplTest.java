package com.restaurant.RestaurantMicroservice.service.impl;

import com.restaurant.RestaurantMicroservice.constants.Constants;
import com.restaurant.RestaurantMicroservice.dtos.CategoryResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateCategoryRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.UpdateCategoryRequestDto;
import com.restaurant.RestaurantMicroservice.entities.Category;
import com.restaurant.RestaurantMicroservice.entities.MenuItems;
import com.restaurant.RestaurantMicroservice.entities.RestaurantDetail;
import com.restaurant.RestaurantMicroservice.exception.ConflictException;
import com.restaurant.RestaurantMicroservice.exception.NotFoundException;
import com.restaurant.RestaurantMicroservice.repository.CategoryRepository;
import com.restaurant.RestaurantMicroservice.repository.MenuItemRepository;
import com.restaurant.RestaurantMicroservice.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    private RestaurantDetail restaurant;
    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurant = new RestaurantDetail();
        restaurant.setId(1);
        category = new Category();
        category.setId(1);
        category.setName("Pizza");
        category.setRestaurantId(1);
    }

    @Test
    void createCategory_ShouldCreateCategory_WhenValidRequest() {
        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto();
        requestDto.setName("Pizza");
        requestDto.setRestaurantId(1);

        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(categoryRepository.existsByNameAndRestaurantId("Pizza", 1)).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CommonResponseDto response = categoryService.createCategory(requestDto);

        assertEquals(Constants.ADDED_SUCCESSFULLY, response.getMessage());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void createCategory_ShouldThrowNotFound_WhenRestaurantNotFound() {
        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto();
        requestDto.setName("Pizza");
        requestDto.setRestaurantId(1);

        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            categoryService.createCategory(requestDto);
        });

        assertEquals(Constants.CATEGORY_NOT_FOUND, exception.getMessage());
    }

    /*@Test
    void createCategory_ShouldThrowConflict_WhenCategoryExists() {
        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto();
        requestDto.setName("Pizza");
        requestDto.setRestaurantId(1);

        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(categoryRepository.existsByNameAndRestaurantId("Pizza", 1)).thenReturn(true);

        ConflictException exception = assertThrows(ConflictException.class, () -> {
            categoryService.createCategory(requestDto);
        });

        assertEquals(Constants.CATEGORY_ALREADY_EXIST + " Pizza for this restaurant.", exception.getMessage());
    }*/

    @Test
    void updateCategory_ShouldUpdateCategory_WhenValidRequest() {
        UpdateCategoryRequestDto requestDto = new UpdateCategoryRequestDto();
        requestDto.setName("Burger");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(categoryRepository.existsByNameAndRestaurantId("Burger", 1)).thenReturn(false);

        CommonResponseDto response = categoryService.updateCategory(1, requestDto);

        assertEquals("Category Updated", response.getMessage());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void updateCategory_ShouldThrowNotFound_WhenCategoryNotFound() {
        UpdateCategoryRequestDto requestDto = new UpdateCategoryRequestDto();
        requestDto.setName("Burger");

        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            categoryService.updateCategory(1, requestDto);
        });

        assertEquals(Constants.CATEGORY_NOT_FOUND, exception.getMessage());
    }

  /*  @Test
    void updateCategory_ShouldThrowConflict_WhenCategoryNameExists() {
        UpdateCategoryRequestDto requestDto = new UpdateCategoryRequestDto();
        requestDto.setName("Pizza");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(categoryRepository.existsByNameAndRestaurantId("Pizza", 1)).thenReturn(true);

        ConflictException exception = assertThrows(ConflictException.class, () -> {
            categoryService.updateCategory(1, requestDto);
        });

        assertEquals(Constants.CATEGORY_ALREADY_EXIST, exception.getMessage());
    }*/
/*  @Test
  void updateCategory_ShouldThrowConflict_WhenCategoryNameExists() {
      UpdateCategoryRequestDto requestDto = new UpdateCategoryRequestDto();
      requestDto.setName("Pizza");

      when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
      when(categoryRepository.existsByNameAndRestaurantId("Pizza", 1)).thenReturn(true);

      ConflictException exception = assertThrows(ConflictException.class, () -> {
          categoryService.updateCategory(1, requestDto);
      });

      assertEquals(Constants.CATEGORY_ALREADY_EXIST, exception.getMessage());
  }*/


    @Test
    void getAllCategoriesByRestaurantId_ShouldReturnCategories_WhenValidRestaurantId() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(categoryRepository.findByRestaurantId(1)).thenReturn(Collections.singletonList(category));

        List<CategoryResponseDto> categories = categoryService.getAllCategoriesByRestaurantId(1);

        assertEquals(1, categories.size());
        assertEquals("Pizza", categories.get(0).getName());
    }

    @Test
    void getAllCategoriesByRestaurantId_ShouldThrowNotFound_WhenRestaurantNotFound() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            categoryService.getAllCategoriesByRestaurantId(1);
        });

        assertEquals(Constants.CATEGORY_NOT_FOUND, exception.getMessage());
    }

    @Test
    void deleteCategory_ShouldDeleteCategory_WhenCategoryExists() {
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(menuItemRepository.findByCategoryId(1)).thenReturn(new ArrayList<>());

        CommonResponseDto response = categoryService.deleteCategory(1);

        assertEquals("Category Deleted", response.getMessage());
        verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    void deleteCategory_ShouldThrowNotFound_WhenCategoryNotFound() {
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            categoryService.deleteCategory(1);
        });

        assertEquals(Constants.CATEGORY_NOT_FOUND, exception.getMessage());
    }

    @Test
    void getCategoriesById_ShouldReturnCategory_WhenCategoryExists() {
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

        CategoryResponseDto response = categoryService.getCategoriesById(1);

        assertEquals("Pizza", response.getName());
        assertEquals(1, response.getId());
    }

    @Test
    void getCategoriesById_ShouldThrowNotFound_WhenCategoryNotFound() {
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            categoryService.getCategoriesById(1);
        });

        assertEquals(Constants.CATEGORY_NOT_FOUND, exception.getMessage());
    }
}
