package com.restaurant.RestaurantMicroservice.controller;

import com.restaurant.RestaurantMicroservice.dtos.CategoryResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateCategoryRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.UpdateCategoryRequestDto;
import com.restaurant.RestaurantMicroservice.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Test class for {@link CategoryController}.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    /**
     * MockMvc object for simulating HTTP requests in tests.
     */
    private MockMvc mockMvc;

    /**
     * The controller being tested.
     */
    @InjectMocks
    private CategoryController categoryController;

    /**
     * Mocked service for handling category-related business logic.
     */
    @Mock
    private CategoryService categoryService;

    /**
     * Sets up the mockMvc object before each test.
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    /**
     * Test case for successfully creating a category.
     */
    @Test
    public void createCategorySuccess() throws Exception {
        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto();
        requestDto.setName("Appetizers");

        CommonResponseDto responseDto = new CommonResponseDto();
        responseDto.setMessage("Category created successfully");

        when(categoryService.createCategory(requestDto)).thenReturn(responseDto);

        mockMvc.perform(post("/api/categories/addCategory")
                        .contentType("application/json")
                        .content("{\"name\":\"Appetizers\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message")
                        .value("Category created successfully"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * Test case for successfully updating a category.
     */
    @Test
    public void updateCategorySuccess() throws Exception {
        UpdateCategoryRequestDto updateDto = new UpdateCategoryRequestDto();
        updateDto.setName("Desserts");

        CommonResponseDto responseDto = new CommonResponseDto();
        responseDto.setMessage("Category updated successfully");

        when(categoryService.updateCategory(1, updateDto)).thenReturn(responseDto);

        mockMvc.perform(put("/api/categories/update/category/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Desserts\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Category updated successfully"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * Test case for fetching all categories by restaurant ID.
     */
    @Test
    public void getAllCategoriesByRestaurantId() throws Exception {
        CategoryResponseDto category1 = new CategoryResponseDto();
        category1.setId(1);
        category1.setName("Appetizers");

        CategoryResponseDto category2 = new CategoryResponseDto();
        category2.setId(2);
        category2.setName("Main Course");

        List<CategoryResponseDto> categories = Arrays.asList(category1, category2);

        when(categoryService.getAllCategoriesByRestaurantId(1)).thenReturn(categories);

        mockMvc.perform(get("/api/categories/getAllCategory/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Appetizers"))
                .andExpect(jsonPath("$[1].name").value("Main Course"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * Test case for successfully deleting a category.
     */
    @Test
    public void deleteCategorySuccess() throws Exception {
        CommonResponseDto responseDto = new CommonResponseDto();
        responseDto.setMessage("Category deleted successfully");

        when(categoryService.deleteCategory(1)).thenReturn(responseDto);

        mockMvc.perform(delete("/api/categories/deleteCategory/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Category deleted successfully"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * Test case for fetching category details by its ID.
     */
    @Test
    public void getCategoryById() throws Exception {
        CategoryResponseDto categoryDto = new CategoryResponseDto();
        categoryDto.setId(1);
        categoryDto.setName("Appetizers");

        when(categoryService.getCategoriesById(1)).thenReturn(categoryDto);

        mockMvc.perform(get("/api/categories/getCategoryById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Appetizers"))
                .andDo(MockMvcResultHandlers.print());
    }
}
