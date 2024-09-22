package com.restaurant.RestaurantMicroservice.service.impl;

import com.restaurant.RestaurantMicroservice.FeignClient.UserFeignClient;
import com.restaurant.RestaurantMicroservice.constants.Constants;
import com.restaurant.RestaurantMicroservice.dtos.CreateRestaurantDetailRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.RestaurantDetailUpdateRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.RestaurantResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.UserResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.entities.RestaurantDetail;
import com.restaurant.RestaurantMicroservice.enums.RoleType;
import com.restaurant.RestaurantMicroservice.exception.NotFoundException;
import com.restaurant.RestaurantMicroservice.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Unit tests for the {@link RestaurantServiceImpl} class.
 */
public class RestaurantDetailServiceImplTest {

    /**
     * {@link RestaurantServiceImpl} instance under test.
     */
    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    /**
     * Mocked {@link RestaurantRepository} instance.
     */
    @Mock
    private RestaurantRepository restaurantRepository;

    /**
     * Mocked {@link UserFeignClient} instance.
     */
    @Mock
    private UserFeignClient userFeignClient;

    /**
     * Mocked {@link MultipartFile} instance.
     */
    @Mock
    private MultipartFile multipartFile;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the successful creation of a restaurant.
     * <p>
     * This test verifies that a restaurant is created successfully when
     * all required conditions are met, including checking if the user
     * exists, and the restaurant name does not already exist.
     * </p>
     * @throws IOException if an error occurs during file operations
     */
    @Test
    public void testCreateRestaurantSuccess() throws IOException {
        CreateRestaurantDetailRequestDto dto = new CreateRestaurantDetailRequestDto();
        dto.setRestaurantName("Test Restaurant");
        dto.setUserId(1);
        dto.setAddress("123 Test St");
        dto.setContactInformation("1234567890");
        dto.setDescription("Test Description");
        dto.setOpeningHours("9 AM - 9 PM");

        UserResponseDto userResponse = new UserResponseDto();
        userResponse.setRole(RoleType.RESTAURANT_OWNER);

        when(userFeignClient.getUserById(1)).thenReturn(userResponse);
        when(restaurantRepository.existsByRestaurantNameIgnoreCase("Test Restaurant")).thenReturn(false);

        when(multipartFile.getContentType()).thenReturn("image/jpeg");
        when(multipartFile.getSize()).thenReturn(5 * 1024 * 1024L);
        when(multipartFile.getBytes()).thenReturn("dummy".getBytes());

        CommonResponseDto response = restaurantService.createRestaurant(dto, multipartFile);

        assertEquals(Constants.RESTAURANT_CREATED_SUCCESSFULLY, response.getMessage());
        verify(restaurantRepository, times(1)).save(any(RestaurantDetail.class));
    }

    /**
     * Tests the case where the user is not found when creating a restaurant.
     * <p>
     * This test verifies that a {@link NotFoundException} is thrown when the
     * user with the specified ID is not found in the user service.
     * </p>
     */
    @Test
    public void testCreateRestaurantUserNotFound() {
        CreateRestaurantDetailRequestDto dto = new CreateRestaurantDetailRequestDto();
        dto.setRestaurantName("Test Restaurant");
        dto.setUserId(1);

        when(userFeignClient.getUserById(1)).thenThrow(new NotFoundException(Constants.USER_NOT_FOUND));

        assertThrows(NotFoundException.class, () -> restaurantService.createRestaurant(dto, null));
    }

    /**
     * Tests the successful update of a restaurant.
     * <p>
     * This test verifies that a restaurant is updated successfully when
     * it exists and the new restaurant name does not already exist.
     * </p>
     * @throws IOException if an error occurs during file operations
     */
    @Test
    public void testUpdateRestaurantSuccess() throws IOException {
        RestaurantDetail existingRestaurant = new RestaurantDetail();
        existingRestaurant.setId(1);
        existingRestaurant.setRestaurantName("Old Restaurant");

        RestaurantDetailUpdateRequestDto updateDto = new RestaurantDetailUpdateRequestDto();
        updateDto.setRestaurantName("Updated Restaurant");
        updateDto.setAddress("123 New St");
        updateDto.setOpeningHours("10 AM - 10 PM");
        updateDto.setContactInformation("0987654321");
        updateDto.setDescription("Updated Description");

        when(restaurantRepository.findById(1)).thenReturn(Optional.of(existingRestaurant));
        when(restaurantRepository.existsByRestaurantNameIgnoreCase("Updated Restaurant")).thenReturn(false);

        when(multipartFile.getContentType()).thenReturn("image/png");
        when(multipartFile.getSize()).thenReturn(5 * 1024 * 1024L);
        when(multipartFile.getBytes()).thenReturn("updated".getBytes());

        CommonResponseDto response = restaurantService.updateRestaurant(1, updateDto, multipartFile);

        assertEquals(Constants.UPDATED_SUCCESSFULLY, response.getMessage());
        verify(restaurantRepository, times(1)).save(existingRestaurant);
    }

    /**
     * Tests the case where the restaurant to update is not found.
     * <p>
     * This test verifies that a {@link NotFoundException} is thrown when
     * the restaurant with the specified ID is not found in the repository.
     * </p>
     */
    @Test
    public void testUpdateRestaurantNotFound() {
        RestaurantDetailUpdateRequestDto updateDto = new RestaurantDetailUpdateRequestDto();
        updateDto.setRestaurantName("Updated Restaurant");

        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> restaurantService.updateRestaurant(1, updateDto, null));
    }

    /**
     * Tests the successful deletion of a restaurant.
     * <p>
     * This test verifies that a restaurant is deleted successfully when
     * it exists in the repository.
     * </p>
     */
    @Test
    public void testDeleteRestaurantSuccess() {
        RestaurantDetail existingRestaurant = new RestaurantDetail();
        existingRestaurant.setId(1);
        existingRestaurant.setRestaurantName("Test Restaurant");

        when(restaurantRepository.findById(1)).thenReturn(Optional.of(existingRestaurant));

        CommonResponseDto response = restaurantService.deleteRestaurant(1);

        assertEquals(Constants.DELETED_SUCCESSFULLY, response.getMessage());
        verify(restaurantRepository, times(1)).delete(existingRestaurant);
    }

    /**
     * Tests the case where the restaurant to delete is not found.
     * <p>
     * This test verifies that a {@link NotFoundException} is thrown when
     * the restaurant with the specified ID is not found in the repository.
     * </p>
     */
    @Test
    public void testDeleteRestaurantNotFound() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> restaurantService.deleteRestaurant(1));
    }

    /**
     * Tests the successful retrieval of a restaurant by its ID.
     * <p>
     * This test verifies that the correct restaurant details are returned
     * when the restaurant with the specified ID is found in the repository.
     * </p>
     */
    @Test
    public void testGetRestaurantByIdSuccess() {
        RestaurantDetail existingRestaurant = new RestaurantDetail();
        existingRestaurant.setId(1);
        existingRestaurant.setRestaurantName("Test Restaurant");

        when(restaurantRepository.findById(1)).thenReturn(Optional.of(existingRestaurant));

        RestaurantResponseDto response = restaurantService.getRestaurantById(1);

        assertEquals("Test Restaurant", response.getRestaurantName());
    }

    /**
     * Tests the case where the restaurant to retrieve by ID is not found.
     * <p>
     * This test verifies that a {@link NotFoundException} is thrown when
     * the restaurant with the specified ID is not found in the repository.
     * </p>
     */
    @Test
    public void testGetRestaurantByIdNotFound() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> restaurantService.getRestaurantById(1));
    }

    /**
     * Tests the successful retrieval of all restaurants.
     * <p>
     * This test verifies that all restaurants are retrieved successfully
     * from the repository.
     * </p>
     */
    @Test
    public void testGetAllRestaurantsSuccess() {
        RestaurantDetail restaurant1 = new RestaurantDetail();
        restaurant1.setRestaurantName("Restaurant 1");

        RestaurantDetail restaurant2 = new RestaurantDetail();
        restaurant2.setRestaurantName("Restaurant 2");

        List<RestaurantDetail> restaurants = new ArrayList<>();
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

        when(restaurantRepository.findAll()).thenReturn(restaurants);

        List<RestaurantResponseDto> response = restaurantService.getAllRestaurants();

        assertEquals(2, response.size());
        assertEquals("Restaurant 1", response.get(0).getRestaurantName());
        assertEquals("Restaurant 2", response.get(1).getRestaurantName());
    }
}
