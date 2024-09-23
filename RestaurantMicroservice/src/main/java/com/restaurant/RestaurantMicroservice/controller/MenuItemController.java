package com.restaurant.RestaurantMicroservice.controller;

import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateMenuItemRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.MenuItemResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.MenuItemUpdateRequestDto;
import com.restaurant.RestaurantMicroservice.service.MenuItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for handling menu item-related requests.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/menuItems")
public class MenuItemController {

    /**
     * Logger instance for logging information and errors.
     */
    private static final Logger LOGGER = Logger.getLogger(MenuItemController.class);

    /**
     * Service instance for handling menu item-related business logic.
     */
    private final MenuItemService menuItemService;

    /**
     * Constructor to initialize the MenuItemController with the MenuItemService.
     * @param menuItemService Service for menu item-related operations.
     */
    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    /**
     * Adds a new menu item with an optional image file.
     * @param createMenuItemRequestDTO Request body containing menu item details.
     * @param multipartFile Multipart file containing the image for the menu item.
     * @return ResponseEntity containing common response.
     */
    @Transactional
    @PostMapping("/add")
    public ResponseEntity<CommonResponseDto> createMenuItem(
            @Valid @ModelAttribute CreateMenuItemRequestDto createMenuItemRequestDTO,
            @RequestParam("image") MultipartFile multipartFile) {

        LOGGER.info("Received request to create menu item with name: "
                + createMenuItemRequestDTO.getFoodName());

        CommonResponseDto responseDTO = menuItemService.createMenuItem(
                createMenuItemRequestDTO, multipartFile);

        LOGGER.info("Menu item created successfully with response: " + responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Deletes a menu item by ID.
     * @param id Menu item ID.
     * @return ResponseEntity containing common response.
     */
    @DeleteMapping("/deleteMenuItem/{id}")
    public ResponseEntity<CommonResponseDto> deleteMenuItem(@PathVariable int id) {
        LOGGER.info("Received request to delete menu item with ID: " + id);

        CommonResponseDto responseDTO = menuItemService.deleteMenuItem(id);

        LOGGER.info("Menu item deleted successfully with response: " + responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Retrieves a menu item by ID.
     * @param id Menu item ID.
     * @return ResponseEntity containing menu item response.
     */
    @GetMapping("/getMenuItem/{id}")
    public ResponseEntity<MenuItemResponseDto> getMenuItemById(@PathVariable int id) {
        LOGGER.info("Received request to fetch menu item with ID: " + id);

        MenuItemResponseDto response = menuItemService.getMenuItemById(id);

        LOGGER.info("Fetched menu item successfully: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves all menu items for a specific category by category ID.
     * @param categoryId Category ID.
     * @return ResponseEntity containing a list of menu item responses.
     */
    @GetMapping("/menuItemsByCategory/{categoryId}")
    public ResponseEntity<List<MenuItemResponseDto>> getAllMenuItemsByCategoryId(
            @PathVariable int categoryId) {

        LOGGER.info("Received request to fetch all menu items for category ID: "
                + categoryId);

        List<MenuItemResponseDto> responseDTOs = menuItemService
                .getAllMenuItemsByCategoryId(categoryId);

        LOGGER.info("Fetched " + responseDTOs.size() + " menu items for category ID: "
                + categoryId);
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    /**
     * Retrieves all menu items for a specific restaurant by restaurant ID.
     * @param restaurantId Restaurant ID.
     * @return ResponseEntity containing a list of menu item responses.
     */
    @GetMapping("/menuItemsByRestaurant/{restaurantId}")
    public ResponseEntity<List<MenuItemResponseDto>> getAllMenuItemsByRestaurantId(
            @PathVariable int restaurantId) {

        LOGGER.info("Received request to fetch all menu items for restaurant ID: "
                + restaurantId);

        List<MenuItemResponseDto> responseDTOs = menuItemService
                .getAllMenuItemsByRestaurantId(restaurantId);

        LOGGER.info("Fetched " + responseDTOs.size() + " menu items for restaurant ID: "
                + restaurantId);
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    /**
     * Updates the status of a menu item by ID.
     * @param id Menu item ID.
     * @return ResponseEntity containing common response.
     */
    @PutMapping("/menuItem/{id}/status")
    public ResponseEntity<CommonResponseDto> updateMenuItemStatus(@PathVariable int id) {
        LOGGER.info("Received request to update menu item status with ID: " + id);

        CommonResponseDto response = menuItemService.updateMenuItemStatus(id);

        LOGGER.info("Menu item status updated successfully with response: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Updates an existing menu item with optional image file.
     * @param menuItemId Menu item ID.
     * @param menuItemUpdateRequestDTO Request body containing updated menu item details.
     * @param multipartFile Optional multipart file containing the image for the menu item.
     * @return ResponseEntity containing common response.
     */
    @PutMapping("/update/{menuItemId}/menuItem")
    public ResponseEntity<CommonResponseDto> updateMenuItem(
            @PathVariable int menuItemId,
            @Valid @RequestBody MenuItemUpdateRequestDto menuItemUpdateRequestDTO,
            @RequestPart(required = false) MultipartFile multipartFile) {

        LOGGER.info("Received request to update menu item with ID: "
                + menuItemId + " and details: " + menuItemUpdateRequestDTO);

        CommonResponseDto responseDTO = menuItemService.updateMenuItem(
                menuItemId, menuItemUpdateRequestDTO, multipartFile);

        LOGGER.info("Menu item updated successfully with response: " + responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
