package com.restaurant.RestaurantMicroservice.service.impl;

import com.restaurant.RestaurantMicroservice.FeignClient.UserFeignClient;
import com.restaurant.RestaurantMicroservice.constants.Constants;
import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CreateRestaurantDetailRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.RestaurantDetailUpdateRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.RestaurantResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.UserResponseDto;
import com.restaurant.RestaurantMicroservice.entities.RestaurantDetail;
import com.restaurant.RestaurantMicroservice.enums.RoleType;
import com.restaurant.RestaurantMicroservice.exception.ConflictException;
import com.restaurant.RestaurantMicroservice.exception.FailedRequestException;
import com.restaurant.RestaurantMicroservice.exception.ImageProcessingFailedException;
import com.restaurant.RestaurantMicroservice.exception.InvalidFileTypeException;
import com.restaurant.RestaurantMicroservice.exception.NotFoundException;
import com.restaurant.RestaurantMicroservice.exception.UnauthorizedException;
import com.restaurant.RestaurantMicroservice.repository.RestaurantRepository;
import com.restaurant.RestaurantMicroservice.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing restaurants.
 */

@Service
public class RestaurantServiceImpl implements RestaurantService {


    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    /**
     * Repository for managing {@link RestaurantDetail} entities.
     */
    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * Feign client for interacting with the user service.
     */
    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * Creates a new restaurant with the provided details and image.
     *
     * @param createRestaurantRequestDTO DTO containing details for creating a restaurant.
     * @param multipartFile Optional image file for the restaurant.
     * @return A {@link CommonResponseDto} object indicating the success of the creation operation.
     * @throws ConflictException If a restaurant with the same name already exists.
     * @throws NotFoundException If the user is not found or is not authorized to create a restaurant.
     * @throws UnauthorizedException If the user does not have the required role to create a restaurant.
     * @throws InvalidFileTypeException If the image file is of an invalid type or exceeds the size limit.
     * @throws ImageProcessingFailedException If an error occurs while processing the image file.
     */
    @Override
    public CommonResponseDto createRestaurant(CreateRestaurantDetailRequestDto createRestaurantRequestDTO,
                                              MultipartFile multipartFile) {
        LOGGER.info("Creating restaurant: {}", createRestaurantRequestDTO.getRestaurantName());

        String name = createRestaurantRequestDTO.getRestaurantName();

        if (restaurantRepository.existsByRestaurantNameIgnoreCase(name)) {
            LOGGER.warn("Restaurant name already exists: {}", name);
            throw new ConflictException(Constants.RESTAURANT_NAME_ALREADY_EXISTS);
        }

        UserResponseDto userResponseDto;

        try {
            userResponseDto = userFeignClient.getUserById(createRestaurantRequestDTO.getUserId());
        } catch (NotFoundException e) {
            LOGGER.error("User not found: {}", createRestaurantRequestDTO.getUserId());
            throw new NotFoundException(Constants.USER_NOT_FOUND);
        }

        if (userResponseDto.getRole() != RoleType.RESTAURANT_OWNER) {
            LOGGER.error("User {} is not authorized to create a restaurant", userResponseDto.getId());
            throw new UnauthorizedException(Constants.NOT_AUTHORIZED);
        }

        RestaurantDetail restaurant = new RestaurantDetail();

        if (multipartFile != null && !multipartFile.isEmpty()) {
            String contentType = multipartFile.getContentType();
            if (contentType == null
                    || !(contentType.equals("image/jpeg")
                    || contentType.equals("image/jpg")
                    || contentType.equals("image/png"))) {
                LOGGER.error("Invalid file type: {}", contentType);
                throw new InvalidFileTypeException(Constants.INVALID_FILE);
            }

            if (multipartFile.getSize() > 20 * 1024 * 1024) {
                LOGGER.error("File size exceeds the 20 MB limit. File size: {} bytes", multipartFile.getSize());
                throw new InvalidFileTypeException(Constants.FILE_TOO_LARGE);
            }

            try {
                restaurant.setImageData(multipartFile.getBytes());
            } catch (IOException e) {
                LOGGER.error("Error processing image file: {}", e.getMessage());
                throw new ImageProcessingFailedException(Constants.PROCESSING_FAILED);
            }
        }

        restaurant.setAddress(createRestaurantRequestDTO.getAddress());
        restaurant.setRestaurantName(name);
        restaurant.setContactNo(createRestaurantRequestDTO.getContactInformation());
        restaurant.setDescription(createRestaurantRequestDTO.getDescription());
        restaurant.setOpen(false);
        restaurant.setOpeningHours(createRestaurantRequestDTO.getOpeningHours());
        restaurant.setOwnerId(createRestaurantRequestDTO.getUserId());

        restaurantRepository.save(restaurant);
        LOGGER.info("Restaurant created successfully: {}", restaurant.getRestaurantName());

        CommonResponseDto response = new CommonResponseDto();
        response.setMessage(Constants.RESTAURANT_CREATED_SUCCESSFULLY);

        return response;
    }

    /**
     * Updates an existing restaurant with the provided details and image.
     *
     * @param id The ID of the restaurant to be updated.
     * @param restaurantUpdateRequestDTO DTO containing updated details for the restaurant.
     * @param multipartFile Optional image file for the restaurant.
     * @return A {@link CommonResponseDto} object indicating the success of the update operation.
     * @throws NotFoundException If the restaurant is not found.
     * @throws ConflictException If a restaurant with the same name already exists.
     * @throws InvalidFileTypeException If the image file is of an invalid type or exceeds the size limit.
     * @throws ImageProcessingFailedException If an error occurs while processing the image file.
     */

    @Override
    public CommonResponseDto updateRestaurant(int id,
                                              RestaurantDetailUpdateRequestDto restaurantUpdateRequestDTO,
                                              MultipartFile multipartFile) {
        LOGGER.info("Updating restaurant with id: {}", id);

        RestaurantDetail restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Restaurant not found: {}", id);
                    return new NotFoundException(Constants.RESTAURANT_NOT_FOUND);
                });

        String name = restaurantUpdateRequestDTO.getRestaurantName();
        if (!restaurant.getRestaurantName().equalsIgnoreCase(name)
                &&
                restaurantRepository.existsByRestaurantNameIgnoreCase(name)) {
            LOGGER.error("A restaurant with the name {} already exists.", name);
            throw new ConflictException(Constants.RESTAURANT_NAME_ALREADY_EXISTS);
        }

        restaurant.setRestaurantName(restaurantUpdateRequestDTO.getRestaurantName());
        restaurant.setAddress(restaurantUpdateRequestDTO.getAddress());
        restaurant.setOpeningHours(restaurantUpdateRequestDTO.getOpeningHours());
        restaurant.setContactNo(restaurantUpdateRequestDTO.getContactInformation());
        restaurant.setDescription(restaurantUpdateRequestDTO.getDescription());

        if (multipartFile != null && !multipartFile.isEmpty()) {
            String contentType = multipartFile.getContentType();
            if (contentType == null
                    || !(contentType.equals("image/jpeg")
                    || contentType.equals("image/jpg")
                    || contentType.equals("image/png"))) {
                LOGGER.error("Invalid file type: {}", contentType);
                throw new InvalidFileTypeException(Constants.INVALID_FILE);
            }
            if (multipartFile.getSize() > 10 * 1024 * 1024) {
                LOGGER.error("File size exceeds the 10 MB limit. File size: {} bytes", multipartFile.getSize());
                throw new InvalidFileTypeException(Constants.FILE_TOO_LARGE);
            }

            try {
                restaurant.setImageData(multipartFile.getBytes());
            } catch (IOException e) {
                LOGGER.error("Error processing image file: {}", e.getMessage());
                throw new ImageProcessingFailedException(Constants.PROCESSING_FAILED);
            }
        }

        restaurantRepository.save(restaurant);
        LOGGER.info("Restaurant updated successfully: {}", restaurant.getRestaurantName());

        CommonResponseDto message = new CommonResponseDto();
        message.setMessage(Constants.UPDATED_SUCCESSFULLY);

        return message;
    }
    /**
     * Deletes a restaurant by its ID.
     *
     * @param id The ID of the restaurant to be deleted.
     * @return A {@link CommonResponseDto} object indicating the success of the deletion operation.
     * @throws NotFoundException If the restaurant is not found.
     */

    @Override
    public CommonResponseDto deleteRestaurant(int id) {
        LOGGER.info("Deleting restaurant with id: {}", id);

        RestaurantDetail restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Restaurant not found: {}", id);
                    return new NotFoundException(Constants.RESTAURANT_NOT_FOUND);
                });

        restaurantRepository.delete(restaurant);
        LOGGER.info("Restaurant deleted successfully: {}", restaurant.getRestaurantName());

        CommonResponseDto message = new CommonResponseDto();
        message.setMessage(Constants.DELETED_SUCCESSFULLY);

        return message;
    }
    /**
     * Fetches the details of a restaurant by its ID.
     *
     * @param id The ID of the restaurant to be fetched.
     * @return A {@link RestaurantResponseDto} object containing the details of the restaurant.
     * @throws NotFoundException If the restaurant is not found.
     */

    @Override
    public RestaurantResponseDto getRestaurantById(int id) {
        LOGGER.info("Fetching restaurant by id: {}", id);

        RestaurantDetail restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Restaurant not found: {}", id);
                    return new NotFoundException(Constants.RESTAURANT_NOT_FOUND + id);
                });

        RestaurantResponseDto responseDTO = convertToRestaurantResponseDTO(restaurant);
        LOGGER.info("Restaurant fetched: {}", restaurant.getRestaurantName());

        return responseDTO;
    }

    /**
     * Converts a {@link RestaurantDetail} entity to a {@link RestaurantResponseDto} data transfer object.
     *
     * @param restaurant The {@link RestaurantDetail} entity to be converted.
     * @return A {@link RestaurantResponseDto} object containing the details of the restaurant.
     */
    private RestaurantResponseDto convertToRestaurantResponseDTO(RestaurantDetail restaurant) {
        RestaurantResponseDto responseDTO = new RestaurantResponseDto();
        responseDTO.setId(restaurant.getId());
        responseDTO.setRestaurantName(restaurant.getRestaurantName());
        responseDTO.setAddress(restaurant.getAddress());
        responseDTO.setContactNo(restaurant.getContactNo());
        responseDTO.setOpen(restaurant.getOpen());
        responseDTO.setOpeningHours(restaurant.getOpeningHours());
        responseDTO.setImageUrl(restaurant.getImageData());
        responseDTO.setOwnerId(restaurant.getOwnerId());
        return responseDTO;
    }
    /**
     * Updates the status (open/closed) of a restaurant.
     *
     * @param id The ID of the restaurant whose status is to be updated.
     * @return A {@link CommonResponseDto} object indicating the new status of the restaurant.
     * @throws NotFoundException If the restaurant is not found.
     */

    @Override
    public CommonResponseDto updateRestaurantStatus(int id) {
        LOGGER.info("Updating status for restaurant with id: {}", id);

        RestaurantDetail restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Restaurant not found: {}", id);
                    return new NotFoundException(Constants.RESTAURANT_NOT_FOUND + id);
                });

        boolean newStatus = !restaurant.getOpen();
        restaurant.setOpen(newStatus);

        restaurantRepository.save(restaurant);
        LOGGER.info("Restaurant status updated to {}", newStatus ? "open" : "closed");

        String stats = newStatus ? Constants.RESTAURANT_IS_NOW_OPEN : Constants.RESTAURANT_IS_NOW_CLOSED;

        CommonResponseDto message = new CommonResponseDto();
        message.setMessage(stats);

        return message;
    }

    /**
     * Fetches all restaurants owned by a user.
     *
     * @param userId The ID of the user whose restaurants are to be fetched.
     * @return A list of {@link RestaurantResponseDto} objects containing the details of the restaurants.
     * @throws NotFoundException If no restaurants are found for the user.
     */
    @Override
    public List<RestaurantResponseDto> getRestaurantByUserId(int userId) {
        LOGGER.info("Fetching restaurants by user id: {}", userId);

        List<RestaurantDetail> restaurants = restaurantRepository.findByOwnerId(userId);

        if (restaurants.isEmpty()) {
            LOGGER.error("No restaurants found for user id: {}", userId);
            throw new NotFoundException(Constants.RESTAURANT_NOT_FOUND + userId);
        }

        List<RestaurantResponseDto> responseDTOs = restaurants.stream()
                .map(this::convertToRestaurantResponseDTO)
                .collect(Collectors.toList());

        LOGGER.info("{} restaurants found for user id: {}", responseDTOs.size(), userId);

        return responseDTOs;
    }
    /**
     * Fetches all restaurants.
     *
     * @return A list of {@link RestaurantResponseDto} objects containing the details of all restaurants.
     * @throws com.restaurant.RestaurantMicroservice.exception.FailedRequestException
     * If there is a failure in fetching the restaurants.
     */

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        LOGGER.info("Fetching all restaurants");

        try {
            List<RestaurantDetail> restaurants = restaurantRepository.findAll();

            List<RestaurantResponseDto> responseDTOs = restaurants.stream()
                    .map(this::convertToRestaurantResponseDTO)
                    .collect(Collectors.toList());

            LOGGER.info("Fetched {} restaurants", responseDTOs.size());
            return responseDTOs;
        } catch (FailedRequestException e) {
            LOGGER.error("Failed to fetch all restaurants");
            throw new FailedRequestException(Constants.UNABLE_TO_FETCH_ALL_RESTAURANT);
        }
    }
}


