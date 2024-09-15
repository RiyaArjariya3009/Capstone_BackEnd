package com.restaurant.RestaurantMicroservice.controller;

import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateRestaurantDetailRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.RestaurantDetailUpdateRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.RestaurantResponseDto;
import com.restaurant.RestaurantMicroservice.service.RestaurantService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for handling restaurant-related requests.
 */
@CrossOrigin
@RestController
@RequestMapping("api/restaurants")
public class RestaurantController {

    /**
     * Logger instance for logging information and errors.
     */
    private static final Logger LOGGER = Logger.getLogger(RestaurantController.class);

    /**
     * Service instance for handling restaurant-related business logic.
     */
    private final RestaurantService restaurantService;

    /**
     * Constructor to initialize the RestaurantController with the RestaurantService.
     * @param restaurantService Service for restaurant-related operations.
     */
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * Creates a new restaurant with an optional image file.
     * @param createRestaurantRequestDTO Request body containing restaurant details.
     * @param multipartFile Multipart file containing the image for the restaurant.
     * @return ResponseEntity containing common response.
     */
    @PostMapping("/createRestaurants")
    @Transactional
    public ResponseEntity<CommonResponseDto> addRestaurant(
            @Valid @ModelAttribute CreateRestaurantDetailRequestDto createRestaurantRequestDTO,
            @RequestParam("image") MultipartFile multipartFile) {
        LOGGER.info("Received request to create restaurant with name: "
                + createRestaurantRequestDTO.getRestaurantName());

        CommonResponseDto response = restaurantService.createRestaurant(
                createRestaurantRequestDTO, multipartFile);

        LOGGER.info("Restaurant created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Updates an existing restaurant with an optional image file.
     * @param id Restaurant ID.
     * @param restaurantUpdateRequestDTO Request body containing updated restaurant details.
     * @param multipartFile Multipart file containing the image for the restaurant.
     * @return ResponseEntity containing common response.
     */
    @PutMapping("/updateRestaurants/{id}")
    @Transactional
    public ResponseEntity<CommonResponseDto> updateRestaurant(
            @PathVariable int id,
            @Valid @ModelAttribute RestaurantDetailUpdateRequestDto restaurantUpdateRequestDTO,
            @RequestParam("image") MultipartFile multipartFile) {
        LOGGER.info("Received request to update restaurant with ID: " + id);

        CommonResponseDto response = restaurantService.updateRestaurant(
                id, restaurantUpdateRequestDTO, multipartFile);

        LOGGER.info("Restaurant updated successfully with ID: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes a restaurant by ID.
     * @param id Restaurant ID.
     * @return ResponseEntity containing common response.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommonResponseDto> deleteRestaurant(@PathVariable int id) {
        LOGGER.info("Received request to delete restaurant with ID: " + id);

        CommonResponseDto responseDTO = restaurantService.deleteRestaurant(id);

        LOGGER.info("Restaurant deleted successfully with ID: " + id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Retrieves a restaurant by ID.
     * @param id Restaurant ID.
     * @return ResponseEntity containing restaurant response.
     */
    @GetMapping("/getRestaurant/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable int id) {
        LOGGER.info("Received request to fetch restaurant details for ID: " + id);

        RestaurantResponseDto response = restaurantService.getRestaurantById(id);

        LOGGER.info("Fetched restaurant details for ID: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Updates the status of a restaurant by ID.
     * @param id Restaurant ID.
     * @return ResponseEntity containing common response.
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<CommonResponseDto> updateRestaurantStatus(@PathVariable int id) {
        LOGGER.info("Received request to update status for restaurant ID: " + id);

        CommonResponseDto response = restaurantService.updateRestaurantStatus(id);

        LOGGER.info("Restaurant status updated successfully for ID: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves all restaurants associated with a specific user by user ID.
     * @param userId User ID.
     * @return ResponseEntity containing a list of restaurant responses.
     */
    @GetMapping("/user/{userId}")
    @Transactional
    public ResponseEntity<List<RestaurantResponseDto>> getRestaurantByUserId(@PathVariable int userId) {
        LOGGER.info("Received request to fetch restaurants for user ID: " + userId);

        List<RestaurantResponseDto> response = restaurantService.getRestaurantByUserId(userId);

        LOGGER.info("Fetched restaurants for user ID: " + userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves all restaurants.
     * @return ResponseEntity containing a list of restaurant responses.
     */
    @GetMapping("/getAllRestaurant")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        LOGGER.info("Received request to fetch all restaurants");

        List<RestaurantResponseDto> response = restaurantService.getAllRestaurants();

        LOGGER.info("Fetched all restaurant details");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
