package com.restaurant.RestaurantMicroservice.controller;

import com.restaurant.RestaurantMicroservice.dtos.CategoryResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateCategoryRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.UpdateCategoryRequestDto;
import com.restaurant.RestaurantMicroservice.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for handling category-related requests.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    /**
     * Logger instance for logging information and errors.
     */
    private static final Logger LOGGER = Logger.getLogger(CategoryController.class);

    /**
     * Service instance for handling category-related business logic.
     */
    private final CategoryService categoryService;

    /**
     * Constructor to initialize the CategoryController with the CategoryService.
     * @param categoryService Service for category-related operations.
     */
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Adds a new category.
     * @param createCategoryRequestDTO Request body containing category details.
     * @return ResponseEntity containing common response.
     */
    @PostMapping("/addCategory")
    public ResponseEntity<CommonResponseDto> createCategory(
            @Valid @RequestBody CreateCategoryRequestDto createCategoryRequestDTO) {

        LOGGER.info("Entering createCategory method with payload: "
                + createCategoryRequestDTO);

        CommonResponseDto responseDTO = categoryService.createCategory(
                createCategoryRequestDTO);

        LOGGER.info("Category created successfully. Response: " + responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Updates an existing category by ID.
     * @param id Category ID.
     * @param updateCategoryRequestDTO Request body containing updated category details.
     * @return ResponseEntity containing common response.
     */
    @PutMapping("/update/category/{id}")
    public ResponseEntity<CommonResponseDto> updateCategory(
            @PathVariable int id,
            @RequestBody UpdateCategoryRequestDto updateCategoryRequestDTO) {

        LOGGER.info("Entering updateCategory method with ID: " + id
                + " and payload: " + updateCategoryRequestDTO);

        CommonResponseDto response = categoryService.updateCategory(
                id, updateCategoryRequestDTO);

        LOGGER.info("Category updated successfully. Response: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves all categories for a specific restaurant by restaurant ID.
     * @param restaurantId Restaurant ID.
     * @return ResponseEntity containing a list of category responses.
     */
    @GetMapping("/getAllCategory/{restaurantId}")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategoriesByRestaurantId(
            @PathVariable int restaurantId) {

        LOGGER.info("Entering getAllCategoriesByRestaurantId method with Restaurant ID: "
                + restaurantId);

        List<CategoryResponseDto> response = categoryService
                .getAllCategoriesByRestaurantId(restaurantId);

        LOGGER.info("Fetched all categories successfully. Response size: "
                + response.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes a category by ID.
     * @param id Category ID.
     * @return ResponseEntity containing common response.
     */
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<CommonResponseDto> deleteCategory(@PathVariable int id) {
        LOGGER.info("Entering deleteCategory method with ID: " + id);

        CommonResponseDto responseDTO = categoryService.deleteCategory(id);

        LOGGER.info("Category deleted successfully. Response: " + responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Retrieves a specific category by ID.
     * @param id Category ID.
     * @return ResponseEntity containing category response.
     */
    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoriesById(@PathVariable int id) {
        LOGGER.info("Entering getCategoriesById method with ID: " + id);

        CategoryResponseDto response = categoryService.getCategoriesById(id);

        LOGGER.info("Fetched category details successfully. Response: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
