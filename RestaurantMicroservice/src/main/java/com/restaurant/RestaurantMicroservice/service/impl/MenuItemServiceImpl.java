package com.restaurant.RestaurantMicroservice.service.impl;

import com.restaurant.RestaurantMicroservice.constants.Constants;
import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateMenuItemRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.MenuItemResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.MenuItemUpdateRequestDto;
import com.restaurant.RestaurantMicroservice.entities.Category;
import com.restaurant.RestaurantMicroservice.entities.MenuItems;
import com.restaurant.RestaurantMicroservice.entities.RestaurantDetail;
import com.restaurant.RestaurantMicroservice.exception.ConflictException;
import com.restaurant.RestaurantMicroservice.exception.ImageProcessingFailedException;
import com.restaurant.RestaurantMicroservice.exception.InvalidFileTypeException;
import com.restaurant.RestaurantMicroservice.exception.NotFoundException;
import com.restaurant.RestaurantMicroservice.repository.CategoryRepository;
import com.restaurant.RestaurantMicroservice.repository.MenuItemRepository;
import com.restaurant.RestaurantMicroservice.repository.RestaurantRepository;
import com.restaurant.RestaurantMicroservice.service.MenuItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link MenuItemService} interface for managing menu items.
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {


    /**
     * Logger instance for logging information, warnings, and errors in the MenuItemServiceImpl class.
     */
    private static final Logger LOGGER = Logger.getLogger(MenuItemServiceImpl.class.getName());

    /**
     * Repository for performing CRUD operations on the Category entity.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Repository for performing CRUD operations on the Restaurant entity.
     */
    private final RestaurantRepository restaurantRepository;

    /**
     * Repository for performing CRUD operations on the MenuItem entity.
     */
    private final MenuItemRepository menuItemRepository;

    /**
     * Constructs a {@link MenuItemServiceImpl} with the specified repositories.
     *
     * @param categoryRepository   The repository for category entities.
     * @param restaurantRepository The repository for restaurant entities.
     * @param menuItemRepository   The repository for menu item entities.
     */
    @Autowired
    public MenuItemServiceImpl(CategoryRepository categoryRepository, RestaurantRepository restaurantRepository,
                               MenuItemRepository menuItemRepository) {
        this.categoryRepository = categoryRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    /**
     * Creates a new menu item.
     *
     * @param createMenuItemRequestDTO The request DTO containing the details of the menu item to be created.
     * @param multipartFile            The image file for the menu item, if any.
     * @return A {@link CommonResponseDto} indicating the result of the operation.
     */
    @Override
    public CommonResponseDto createMenuItem(CreateMenuItemRequestDto createMenuItemRequestDTO, MultipartFile multipartFile) {
        LOGGER.info("Creating menu item for restaurant ID: " + createMenuItemRequestDTO.getRestaurantId());

        RestaurantDetail restaurant = restaurantRepository.findById(createMenuItemRequestDTO.getRestaurantId())
                .orElseThrow(() -> {
                    LOGGER.error("Restaurant not found with ID: " + createMenuItemRequestDTO.getRestaurantId());
                    return new NotFoundException(Constants.RESTAURANT_NOT_FOUND);
                });

        Category category = categoryRepository.findById(createMenuItemRequestDTO.getCategoryId())
                .orElseThrow(() -> {
                    LOGGER.error("Category not found with ID: " + createMenuItemRequestDTO.getCategoryId());
                    return new NotFoundException(Constants.CATEGORY_NOT_FOUND);
                });

        boolean menuItemExists = menuItemRepository.existsByFoodNameAndRestaurantId(
                createMenuItemRequestDTO.getFoodName(),
                createMenuItemRequestDTO.getRestaurantId()
        );
        if (menuItemExists) {
            LOGGER.error("Menu item already exists with the same food name for "
                    +
                    "restaurant ID: " + createMenuItemRequestDTO.getRestaurantId());
            throw new ConflictException(Constants.MENU_ITEM_ALREADY_EXIST);
        }

        MenuItems menuItem = new MenuItems();
        menuItem.setRestaurantId(restaurant.getId());
        menuItem.setCategoryId(createMenuItemRequestDTO.getCategoryId());
        menuItem.setFoodName(createMenuItemRequestDTO.getFoodName());
        menuItem.setDescription(createMenuItemRequestDTO.getDescription());
        menuItem.setAvailable(false);
        menuItem.setPrice(createMenuItemRequestDTO.getPrice());

        if (multipartFile != null && !multipartFile.isEmpty()) {
            String contentType = multipartFile.getContentType();
            if (contentType == null
                    || !(contentType.equals("image/jpeg")
                    || contentType.equals("image/jpg")
                    || contentType.equals("image/png"))) {

                LOGGER.error("Invalid file type: " + contentType);
                throw new InvalidFileTypeException(Constants.INVALID_FILE);
            }

            if (multipartFile.getSize() > 20 * 1024 * 1024) {
                LOGGER.error("File size exceeds the limit of 10MB. File size: " + multipartFile.getSize());
                throw new InvalidFileTypeException(Constants.FILE_TOO_LARGE);
            }

            try {
                menuItem.setImageData(multipartFile.getBytes());
            } catch (IOException e) {
                LOGGER.error("Error processing image file: " + e.getMessage());
                throw new ImageProcessingFailedException(Constants.PROCESSING_FAILED);
            }
        }

        MenuItems savedMenuItem = menuItemRepository.save(menuItem);
        LOGGER.info("Menu item created successfully with ID: " + savedMenuItem.getId());

        CommonResponseDto responseDTO = new CommonResponseDto();
        responseDTO.setMessage(Constants.MENU_ITEM_ADDED);
        return responseDTO;
    }

    /**
     * Deletes a menu item by its ID.
     *
     * @param id The ID of the menu item to be deleted.
     * @return A {@link CommonResponseDto} indicating the result of the operation.
     */
    @Override
    public CommonResponseDto deleteMenuItem(int id) {
        LOGGER.info("Deleting menu item with ID: ");

        MenuItems menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Menu item not found with ID: " + id);
                    return new NotFoundException(Constants.MENUITEM_NOT_FOUND);
                });

        menuItemRepository.delete(menuItem);
        LOGGER.info("Menu item deleted with ID: " + id);

        CommonResponseDto message = new CommonResponseDto();
        message.setMessage("Menu Item Deleted");
        return message;
    }

    /**
     * Retrieves all menu items by category ID.
     *
     * @param categoryId The ID of the category.
     * @return A list of {@link MenuItemResponseDto} representing the menu items.
     */
    @Transactional
    @Override
    public List<MenuItemResponseDto> getAllMenuItemsByCategoryId(int categoryId) {
        LOGGER.info("Fetching all menu items for category ID: " + categoryId);

        List<MenuItems> menuItems = menuItemRepository.findByCategoryId(categoryId);
        if (menuItems.isEmpty()) {
            LOGGER.warn("No menu items found for category ID: {}");
        }

        List<MenuItemResponseDto> responseDTOs = menuItems.stream()
                .map(this::convertToMenuItemResponseDTO)
                .collect(Collectors.toList());

        LOGGER.info("Successfully fetched " + responseDTOs.size() + " menu items for category ID: " + categoryId);
        return responseDTOs;
    }

    /**
     * Retrieves all menu items by restaurant ID.
     *
     * @param restaurantId The ID of the restaurant.
     * @return A list of {@link MenuItemResponseDto} representing the menu items.
     */
    @Transactional
    @Override
    public List<MenuItemResponseDto> getAllMenuItemsByRestaurantId(int restaurantId) {
        LOGGER.info("Fetching all menu items for restaurant ID: " + restaurantId);

        List<MenuItems> menuItems = menuItemRepository.findByRestaurantId(restaurantId);

        if (menuItems.isEmpty()) {
            LOGGER.warn("No menu items found for restaurant ID");
        }
        List<MenuItemResponseDto> responseDTOs = menuItems.stream()
                .map(this::convertToMenuItemResponseDTO)
                .collect(Collectors.toList());

        LOGGER.info("Successfully fetched " + responseDTOs.size() + " menu items for restaurant ID: " + restaurantId);
        return responseDTOs;
    }

    /**
     * Updates the availability status of a menu item.
     *
     * @param id The ID of the menu item to be updated.
     * @return A {@link CommonResponseDto} indicating the result of the operation.
     */
    @Override
    public CommonResponseDto updateMenuItemStatus(int id) {
        LOGGER.info("Updating availability status for menu item ID: " + id);

        MenuItems menuItems = menuItemRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Menu item not found with ID: " + id);
                    return new NotFoundException(Constants.MENUITEM_NOT_FOUND);
                });

        boolean newStatus = !menuItems.getAvailable();
        menuItems.setAvailable(newStatus);
        menuItemRepository.save(menuItems);

        String statusMessage = newStatus ? Constants.MENU_ITEM_AVAILABLE : Constants.MENU_ITEM_NOTAVAILABLE;
        LOGGER.info("Menu item status updated to: " + statusMessage + " for ID: " + id);

        CommonResponseDto message = new CommonResponseDto();
        message.setMessage(statusMessage);
        return message;
    }

    /**
     * Retrieves a menu item by its ID.
     *
     * @param id The ID of the menu item.
     * @return A {@link MenuItemResponseDto} representing the menu item.
     */
    @Override
    public MenuItemResponseDto getMenuItemById(int id) {
        LOGGER.info("Fetching menu item by ID: " + id);

        MenuItems menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Menu item not found with ID: " + id);
                    return new NotFoundException(Constants.MENUITEM_NOT_FOUND);
                });

        MenuItemResponseDto responseDto = new MenuItemResponseDto();
        responseDto.setFoodName(menuItem.getFoodName());
        responseDto.setDescription(menuItem.getDescription());
        responseDto.setPrice(menuItem.getPrice());
        responseDto.setIsAvailable(menuItem.getAvailable());

        LOGGER.info("Menu item fetched successfully with ID: " + id);
        return responseDto;
    }
    /**
     * Updates an existing menu item with the provided details.
     *
     * @param menuItemId The ID of the menu item to be updated.
     * @param menuItemupdateInDTO Data transfer object containing the updated details of the menu item.
     * @param multipartFile Optional image file to be associated with the menu item.
     * @return A {@link CommonResponseDto} object containing the response message indicating the success of the update operation.
     * @throws NotFoundException If the menu item or the category associated with it is not found.
     * @throws ConflictException If a menu item with the same name already exists in the same restaurant.
     * @throws InvalidFileTypeException If the provided image file is not of an allowed type or exceeds the allowed size.
     * @throws ImageProcessingFailedException If an error occurs while processing the image file.
     */


    @Override
    public CommonResponseDto updateMenuItem(
            int menuItemId,
            MenuItemUpdateRequestDto menuItemupdateInDTO,
            MultipartFile multipartFile) {

        LOGGER.info("Updating menu item with ID: {}");

        String name = menuItemupdateInDTO.getName();
        menuItemupdateInDTO.setName(name);

        MenuItems menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> {
                    LOGGER.error("Menu item with ID: {} not found");
                    return new NotFoundException(Constants.MENU_ITEM_NOT_FOUND);
                });

        categoryRepository.findById(menuItemupdateInDTO.getCategoryId())
                .orElseThrow(() -> {
                    LOGGER.error("Menu category with ID: {} not found");
                    return new NotFoundException(Constants.CATEGORY_NOT_FOUND); // 404 not found
                });


        // Fetch the restaurant ID from the existing menu item
        int restaurantId = menuItem.getRestaurantId();

        // Check if the name has changed
        if (!menuItem.getFoodName().equals(menuItemupdateInDTO.getName())) {
            // Check if a menu item with the same name exists in the restaurant, excluding the current item
            boolean menuItemExists = menuItemRepository.existsByFoodNameAndRestaurantId(menuItemupdateInDTO.getName(),
                    restaurantId);

            if (menuItemExists) {
                LOGGER.error("Menu item with name {} already exists in restaurant ID {}");
                throw new ConflictException(Constants.MENU_ITEM_ALREADY_EXIST); // 409 conflict
            }
        }

        if (multipartFile != null && !multipartFile.isEmpty()) {
            String contentType = multipartFile.getContentType();
            if (contentType == null
                    || !(contentType.equals("image/jpeg")
                    || contentType.equals("image/jpg")
                    || contentType.equals("image/png"))) {
                LOGGER.error("Invalid file type: {}. Only JPEG, JPG, and PNG files are allowed.");
                throw new InvalidFileTypeException(Constants.INVALID_FILE); // 400 bad request
            }

            if (multipartFile.getSize() > 20 * 1024 * 1024) {
                LOGGER.error("File size exceeds the 10 MB limit. File size: {} bytes");
                throw new InvalidFileTypeException(Constants.FILE_TOO_LARGE); // Custom constant for file size error
            }

            try {
                menuItem.setImageData(multipartFile.getBytes());
            } catch (IOException e) {
                LOGGER.error("Error occurred while processing the image file:");
                throw new ImageProcessingFailedException(Constants.PROCESSING_FAILED); // 500 internal server error
            }
        }

        menuItem.setFoodName(menuItemupdateInDTO.getName());
        menuItem.setPrice(menuItemupdateInDTO.getPrice());
        menuItem.setDescription(menuItemupdateInDTO.getDescription());
        menuItem.setCategoryId(menuItemupdateInDTO.getCategoryId());

        menuItemRepository.save(menuItem);

        CommonResponseDto responseDTO = new CommonResponseDto();

        responseDTO.setMessage(Constants.UPDATED_SUCCESSFULLY);

        LOGGER.info("Successfully updated menu item with ID: {}");
        return responseDTO;
    }


    private MenuItemResponseDto convertToMenuItemResponseDTO(MenuItems menuItem) {
        LOGGER.debug("Converting MenuItems to MenuItemResponseDTO: {} start");

        MenuItemResponseDto responseDTO = new MenuItemResponseDto();

        RestaurantDetail restaurant = restaurantRepository.findById(menuItem.getRestaurantId())
                .orElseThrow(() -> new NotFoundException(
                        Constants.RESTAURANT_NOT_FOUND)); // 404 not found


        String categoryName;
        if (menuItem.getCategoryId() == 0) {
            categoryName = Constants.CATEGORY_NOT_FOUND;
        } else {
            Category category = categoryRepository.findById(menuItem.getCategoryId())
                    .orElseThrow(() -> new NotFoundException(
                            Constants.CATEGORY_NOT_FOUND)); // 404 not found
            categoryName = category.getName();
        }

        responseDTO.setId(menuItem.getId());
        responseDTO.setRestaurantName(restaurant.getRestaurantName());
        responseDTO.setCategoryName(categoryName);
        responseDTO.setFoodName(menuItem.getFoodName());
        responseDTO.setDescription(menuItem.getDescription());
        responseDTO.setIsAvailable(menuItem.getAvailable());
        responseDTO.setImageData(menuItem.getImageData());
        responseDTO.setPrice(menuItem.getPrice());

        //LOGGER.debug("Converting MenuItems to MenuItemResponseDTO: {} end", responseDTO);

        return responseDTO;
    }


}



