package com.restaurant.RestaurantMicroservice.controller;

import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.MenuItemUpdateRequestDto;
import com.restaurant.RestaurantMicroservice.service.MenuItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import com.restaurant.RestaurantMicroservice.dtos.CreateMenuItemRequestDto;
import com.restaurant.RestaurantMicroservice.dtos.MenuItemResponseDto;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class MenuItemControllerTest {

    @InjectMocks
    private MenuItemController menuItemController;

    @Mock
    private MenuItemService menuItemService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(menuItemController).build();
    }

    @Test
    public void testCreateMenuItem_Success() throws Exception {
        CreateMenuItemRequestDto createDto = new CreateMenuItemRequestDto();
        createDto.setFoodName("Pizza");
        createDto.setPrice(new BigDecimal("15.99"));
        createDto.setDescription("Delicious cheese pizza");
        createDto.setCategoryId(1);

        MockMultipartFile file = new MockMultipartFile("image", "pizza.jpg", "image/jpeg", "image content".getBytes());

        CommonResponseDto response = new CommonResponseDto("Menu item created successfully");

        when(menuItemService.createMenuItem(any(CreateMenuItemRequestDto.class), any(MockMultipartFile.class)))
                .thenReturn(response);

        // Convert response to JSON
        String expectedJson = "{\"message\":\"Menu item created successfully\"}";

        mockMvc.perform(multipart("/api/menuItems/add")
                        .file(file)
                        .param("foodName", createDto.getFoodName())
                        .param("price", createDto.getPrice().toString())
                        .param("description", createDto.getDescription())
                        .param("categoryId", String.valueOf(createDto.getCategoryId())))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));  // Check against JSON response

        verify(menuItemService, times(1)).createMenuItem(any(CreateMenuItemRequestDto.class), any(MockMultipartFile.class));
    }


    @Test
    public void testDeleteMenuItem_Success() throws Exception {
        CommonResponseDto response = new CommonResponseDto("Menu item deleted successfully");

        when(menuItemService.deleteMenuItem(1)).thenReturn(response);

        mockMvc.perform(delete("/api/menuItems/deleteMenuItem/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Menu item deleted successfully\"}"));


        verify(menuItemService, times(1)).deleteMenuItem(1);
    }

    @Test
    public void testGetMenuItemById_Success() throws Exception {
        MenuItemResponseDto response = new MenuItemResponseDto();
        response.setId(1);
        response.setFoodName("Pizza");
        response.setPrice(new BigDecimal("15.99"));
        response.setDescription("Delicious cheese pizza");

        when(menuItemService.getMenuItemById(1)).thenReturn(response);

        mockMvc.perform(get("/api/menuItems/getMenuItem/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"foodName\":\"Pizza\",\"price\":15.99,\"description\":\"Delicious cheese pizza\"}"));

        verify(menuItemService, times(1)).getMenuItemById(1);
    }

    @Test
    public void testGetAllMenuItemsByCategoryId_Success() throws Exception {
        // Create a sample MenuItemResponseDto
        MenuItemResponseDto menuItem = new MenuItemResponseDto();
        menuItem.setId(1);
        menuItem.setRestaurantName("Test Restaurant");
        menuItem.setCategoryName("Test Category");
        menuItem.setFoodName("Test Food");
        menuItem.setDescription("Delicious test food");
        menuItem.setIsAvailable(true);
        menuItem.setPrice(new BigDecimal("9.99"));
        menuItem.setImageData(new byte[]{1, 2, 3}); // Sample image data

        List<MenuItemResponseDto> responseDTOs = Collections.singletonList(menuItem);

        // Mocking the service method call
        when(menuItemService.getAllMenuItemsByCategoryId(1)).thenReturn(responseDTOs);

        // Perform the request and assert the response
        String expectedJson = "[{" +
                "\"id\":1," +
                "\"restaurantName\":\"Test Restaurant\"," +
                "\"categoryName\":\"Test Category\"," +
                "\"foodName\":\"Test Food\"," +
                "\"description\":\"Delicious test food\"," +
                "\"isAvailable\":true," +
                "\"price\":9.99," +
                "\"imageData\":\"" + Base64.getEncoder().encodeToString(new byte[]{1, 2, 3}) + "\"" +
                "}]"; // Encode image data to Base64

        mockMvc.perform(get("/api/menuItems/menuItemsByCategory/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        verify(menuItemService, times(1)).getAllMenuItemsByCategoryId(1);
    }

    @Test
    public void testUpdateMenuItemStatus_Success() throws Exception {
        CommonResponseDto response = new CommonResponseDto("Menu item status updated successfully");

        when(menuItemService.updateMenuItemStatus(1)).thenReturn(response);

        // Convert response to JSON
        String expectedJson = "{\"message\":\"Menu item status updated successfully\"}";

        mockMvc.perform(put("/api/menuItems/menuItem/1/status"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));  // Check against JSON response

        verify(menuItemService, times(1)).updateMenuItemStatus(1);
    }
}
