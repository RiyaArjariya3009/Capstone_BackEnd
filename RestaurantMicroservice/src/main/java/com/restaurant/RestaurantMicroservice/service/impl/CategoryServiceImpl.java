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
import com.restaurant.RestaurantMicroservice.exception.FailedRequestException;
import com.restaurant.RestaurantMicroservice.exception.NotFoundException;
import com.restaurant.RestaurantMicroservice.repository.CategoryRepository;
import com.restaurant.RestaurantMicroservice.repository.MenuItemRepository;
import com.restaurant.RestaurantMicroservice.repository.RestaurantRepository;
import com.restaurant.RestaurantMicroservice.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    /**
     * Logger instance for logging information and errors.
     */
    private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);

    /**
     * Repository for performing CRUD operations on {@link Category} entities.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Repository for performing CRUD operations on {@link RestaurantDetail} entities.
     */
    private final RestaurantRepository restaurantRepository;

    /**
     * Repository for performing CRUD operations on {@link MenuItems} entities.
     */
    private final MenuItemRepository menuItemRepository;

    /**
     * Constructs a {@link CategoryServiceImpl} instance with the specified repositories.
     *
     * @param categoryRepository     the {@link CategoryRepository} used for category data access
     * @param restaurantRepository   the {@link RestaurantRepository} used for restaurant data access
     * @param menuItemRepository     the {@link MenuItemRepository} used for menu item data access
     */
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               RestaurantRepository restaurantRepository,
                               MenuItemRepository menuItemRepository) {
        this.categoryRepository = categoryRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    /**
     * Creates a new category based on the provided request data.
     *
     * @param createCategoryRequestDTO the data required to create a new category
     * @return a {@link CommonResponseDto} indicating the result of the creation operation
     */
    @Override
    public CommonResponseDto createCategory(CreateCategoryRequestDto createCategoryRequestDTO) {
        LOGGER.info("Creating category for restaurant ID: " + createCategoryRequestDTO.getRestaurantId());

        RestaurantDetail restaurant = restaurantRepository.findById(createCategoryRequestDTO.getRestaurantId())
                .orElseThrow(() -> {
                    LOGGER.error("Restaurant not found for ID: " + createCategoryRequestDTO.getRestaurantId());
                    return new NotFoundException(Constants.CATEGORY_NOT_FOUND);
                });

        boolean categoryExists = categoryRepository.existsByNameAndRestaurantId(
                createCategoryRequestDTO.getName(),
                createCategoryRequestDTO.getRestaurantId()
        );

        if (categoryExists) {
            LOGGER.warn("Category '"
                    + createCategoryRequestDTO.getName() + "'"
                    + " already exists for restaurant ID: "
                    + createCategoryRequestDTO.getRestaurantId());
            throw new ConflictException(Constants.CATEGORY_ALREADY_EXIST
                    + " " +createCategoryRequestDTO.getName()
                    + " for this restaurant.");
        }

        Category category = new Category();
        category.setName(createCategoryRequestDTO.getName());
        category.setRestaurantId(createCategoryRequestDTO.getRestaurantId());

        Category savedCategory = categoryRepository.save(category);
        LOGGER.info("Category created successfully with ID: " + savedCategory.getId());

        CommonResponseDto responseDTO = new CommonResponseDto();
        responseDTO.setMessage(Constants.ADDED_SUCCESSFULLY);
        return responseDTO;
    }

    /**
     * Updates an existing category identified by its ID with the provided data.
     *
     * @param id the ID of the category to update
     * @param updateCategoryRequestDTO the data to update the category with
     * @return a {@link CommonResponseDto} indicating the result of the update operation
     */
    @Override
    public CommonResponseDto updateCategory(int id, UpdateCategoryRequestDto updateCategoryRequestDTO) {
        LOGGER.info("Updating category with ID: " + id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Category not found for ID: " + id);
                    return new NotFoundException(Constants.CATEGORY_NOT_FOUND);
                });

        if (!category.getName().equals(updateCategoryRequestDTO.getName())) {
            boolean categoryExists = categoryRepository.existsByNameAndRestaurantId(
                    updateCategoryRequestDTO.getName(),
                    category.getRestaurantId()
            );

            if (categoryExists) {
                LOGGER.warn("Category '"
                        + updateCategoryRequestDTO.getName()
                        + "' already exists for restaurant ID: "
                        + category.getRestaurantId());
                throw new ConflictException(Constants.CATEGORY_ALREADY_EXIST);
            }
        }

        category.setName(updateCategoryRequestDTO.getName());
        categoryRepository.save(category);
        LOGGER.info("Category updated successfully with ID: " + id);

        CommonResponseDto responseDTO = new CommonResponseDto();
        responseDTO.setMessage("Category Updated");
        return responseDTO;
    }

    /**
     * Retrieves a list of categories associated with a specific restaurant.
     *
     * @param restaurantId the ID of the restaurant whose categories are to be retrieved
     * @return a list of {@link CategoryResponseDto} representing the categories of the specified restaurant
     */
    @Override
    public List<CategoryResponseDto> getAllCategoriesByRestaurantId(int restaurantId) {
        LOGGER.info("Fetching all categories for restaurant ID: " + restaurantId);

        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> {
                    LOGGER.error("Restaurant not found for ID: " + restaurantId);
                    return new NotFoundException(Constants.CATEGORY_NOT_FOUND);
                });

        List<Category> categories = categoryRepository.findByRestaurantId(restaurantId);

        List<CategoryResponseDto> responseDTOs = categories.stream()
                .map(this::convertToCategoryResponseDTO)
                .collect(Collectors.toList());

        LOGGER.info("Fetched " + responseDTOs.size() + " categories for restaurant ID: " + restaurantId);
        return responseDTOs;
    }

    /**
     * Deletes a category identified by its ID.
     *
     * @param categoryId the ID of the category to delete
     * @return a {@link CommonResponseDto} indicating the result of the deletion operation
     */
    @Override
    public CommonResponseDto deleteCategory(int categoryId) {
        LOGGER.info("Deleting category with ID: " + categoryId);

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> {
                    LOGGER.error("Category not found for ID: " + categoryId);
                    return new NotFoundException(Constants.CATEGORY_NOT_FOUND);
                });

        List<MenuItems> menuItems = menuItemRepository.findByCategoryId(categoryId);
        if (!menuItems.isEmpty()) {
            LOGGER.info("Setting category ID to null for "
                    + menuItems.size() + " menu items associated with category ID: "
                    + categoryId);
            for (MenuItems menuItem : menuItems) {
                menuItem.setCategoryId(0);
                menuItemRepository.save(menuItem);
            }
        }

        categoryRepository.delete(category);
        LOGGER.info("Category deleted successfully with ID: " + categoryId);

        CommonResponseDto message = new CommonResponseDto();
        message.setMessage("Category Deleted");
        return message;
    }

    /**
     * Retrieves the details of a category by its ID.
     *
     * @param id the ID of the category to retrieve
     * @return a {@link CategoryResponseDto} representing the details of the specified category
     */
    @Override
    public CategoryResponseDto getCategoriesById(int id) {
        LOGGER.info("Fetching category by ID: " + id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Category not found for ID: " + id);
                    return new NotFoundException(Constants.CATEGORY_NOT_FOUND);
                });

        CategoryResponseDto categoryResponseDTO = new CategoryResponseDto();
        categoryResponseDTO.setRestaurantId(category.getRestaurantId());
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setId(category.getId());

        LOGGER.info("Fetched category details for ID: " + id);
        return categoryResponseDTO;
    }

    /**
     * Converts a {@link Category} entity to a {@link CategoryResponseDto}.
     *
     * @param category the {@link Category} entity to convert
     * @return a {@link CategoryResponseDto} representing the category
     */
    private CategoryResponseDto convertToCategoryResponseDTO(Category category) {
        CategoryResponseDto responseDTO = new CategoryResponseDto();
        responseDTO.setId(category.getId());
        responseDTO.setName(category.getName());
        responseDTO.setRestaurantId(category.getRestaurantId());
        return responseDTO;
    }
}
