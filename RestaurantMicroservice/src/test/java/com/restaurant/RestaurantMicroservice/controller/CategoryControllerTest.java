package com.restaurant.RestaurantMicroservice.controller;

import com.restaurant.RestaurantMicroservice.dtos.CategoryResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateCategoryRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.UpdateCategoryRequestDto;
import com.restaurant.RestaurantMicroservice.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateCategory() throws Exception {
        // Create mock request and response DTO
        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto();
        requestDto.setName("New Category");

        CommonResponseDto responseDto = new CommonResponseDto("Category created successfully");

        // Mock the service method
        when(categoryService.createCategory(any(CreateCategoryRequestDto.class)))
                .thenReturn(responseDto);

        // Perform the POST request
        mockMvc.perform(post("/api/categories/addCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Category created successfully"));

        // Verify the service method was called
        verify(categoryService, times(1)).createCategory(any(CreateCategoryRequestDto.class));
    }

    @Test
    void testUpdateCategory() throws Exception {
        // Mock request and response
        UpdateCategoryRequestDto requestDto = new UpdateCategoryRequestDto();
        requestDto.setName("Updated Category");

        CommonResponseDto responseDto = new CommonResponseDto("Category updated successfully");

        // Mock the service
        when(categoryService.updateCategory(anyInt(), any(UpdateCategoryRequestDto.class)))
                .thenReturn(responseDto);

        // Perform the PUT request
        mockMvc.perform(put("/api/categories/update/category/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Category updated successfully"));

        // Verify the service method was called
        verify(categoryService, times(1)).updateCategory(eq(1), any(UpdateCategoryRequestDto.class));
    }

    @Test
    void testGetAllCategoriesByRestaurantId() throws Exception {
        // Mock response
        List<CategoryResponseDto> categoryList = new ArrayList<>();
        categoryList.add(new CategoryResponseDto());
        categoryList.get(0).setId(1);
        categoryList.get(0).setName("Category 1");
        categoryList.get(0).setRestaurantId(1);

        categoryList.add(new CategoryResponseDto());
        categoryList.get(1).setId(2);
        categoryList.get(1).setName("Category 2");
        categoryList.get(1).setRestaurantId(1);

        // Mock the service method
        when(categoryService.getAllCategoriesByRestaurantId(1))
                .thenReturn(categoryList);

        // Perform the GET request
        mockMvc.perform(get("/api/categories/getAllCategory/{restaurantId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Category 1"))
                .andExpect(jsonPath("$[0].restaurantId").value(1));

        // Verify the service method was called
        verify(categoryService, times(1)).getAllCategoriesByRestaurantId(1);
    }

    @Test
    void testDeleteCategory() throws Exception {
        // Mock response
        CommonResponseDto responseDto = new CommonResponseDto("Category deleted successfully");

        // Mock the service method
        when(categoryService.deleteCategory(1)).thenReturn(responseDto);

        // Perform the DELETE request
        mockMvc.perform(delete("/api/categories/deleteCategory/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Category deleted successfully"));

        // Verify the service method was called
        verify(categoryService, times(1)).deleteCategory(1);
    }

    @Test
    void testGetCategoryById() throws Exception {
        // Mock response
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(1);
        responseDto.setName("Category 1");
        responseDto.setRestaurantId(1);

        // Mock the service method
        when(categoryService.getCategoriesById(1)).thenReturn(responseDto);

        // Perform the GET request
        mockMvc.perform(get("/api/categories/getCategoryById/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Category 1"))
                .andExpect(jsonPath("$.restaurantId").value(1));

        // Verify the service method was called
        verify(categoryService, times(1)).getCategoriesById(1);
    }
}
