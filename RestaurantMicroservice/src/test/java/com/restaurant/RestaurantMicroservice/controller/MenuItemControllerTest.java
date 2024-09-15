package com.restaurant.RestaurantMicroservice.controller;

import com.restaurant.RestaurantMicroservice.dtos.CommonResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.MenuItemResponseDto;
import com.restaurant.RestaurantMicroservice.dtos.MenuItemUpdateRequestDto;
import com.restaurant.RestaurantMicroservice.service.MenuItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test class for {@link MenuItemController}.
 */
@RunWith(MockitoJUnitRunner.class)
public class MenuItemControllerTest {

    /**
     * MockMvc object for simulating HTTP requests in tests.
     */
    private MockMvc mockMvc;

    /**
     * The controller being tested.
     */
    @InjectMocks
    private MenuItemController menuItemController;

    /**
     * Mocked service for handling menu item business logic.
     */
    @Mock
    private MenuItemService menuItemService;

    /**
     * Sets up the mockMvc object before each test.
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(menuItemController).build();
    }

    /**
     * Test case for successfully deleting a menu item.
     */
    @Test
    public void testDeleteMenuItemSuccess() throws Exception {
        CommonResponseDto responseDto = new CommonResponseDto();
        responseDto.setMessage("Menu item deleted successfully");

        when(menuItemService.deleteMenuItem(1)).thenReturn(responseDto);

        mockMvc.perform(delete("/api/menuItems/deleteMenuItem/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Menu item deleted successfully"));
    }

    /**
     * Test case for retrieving a menu item by ID.
     */
    @Test
    public void testGetMenuItemByIdSuccess() throws Exception {
        MenuItemResponseDto responseDto = new MenuItemResponseDto();
        responseDto.setId(1);
        responseDto.setFoodName("Pizza");

        when(menuItemService.getMenuItemById(1)).thenReturn(responseDto);

        mockMvc.perform(get("/api/menuItems/getMenuItem/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.foodName").value("Pizza"));
    }

    /**
     * Test case for fetching all menu items by category ID.
     */
    @Test
    public void testGetAllMenuItemsByCategoryIdSuccess() throws Exception {
        MenuItemResponseDto item1 = new MenuItemResponseDto();
        item1.setId(1);
        item1.setFoodName("Pizza");

        MenuItemResponseDto item2 = new MenuItemResponseDto();
        item2.setId(2);
        item2.setFoodName("Burger");

        List<MenuItemResponseDto> items = Arrays.asList(item1, item2);

        when(menuItemService.getAllMenuItemsByCategoryId(1)).thenReturn(items);

        mockMvc.perform(get("/api/menuItems/menuItemsByCategory/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].foodName").value("Pizza"))
                .andExpect(jsonPath("$[1].foodName").value("Burger"));
    }

    /**
     * Test case for fetching all menu items by restaurant ID.
     */
    @Test
    public void testGetAllMenuItemsByRestaurantIdSuccess() throws Exception {
        MenuItemResponseDto item1 = new MenuItemResponseDto();
        item1.setId(1);
        item1.setFoodName("Pizza");

        MenuItemResponseDto item2 = new MenuItemResponseDto();
        item2.setId(2);
        item2.setFoodName("Burger");

        List<MenuItemResponseDto> items = Arrays.asList(item1, item2);

        when(menuItemService.getAllMenuItemsByRestaurantId(1)).thenReturn(items);

        mockMvc.perform(get("/api/menuItems/menuItemsByRestaurant/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].foodName").value("Pizza"))
                .andExpect(jsonPath("$[1].foodName").value("Burger"));
    }

    /**
     * Test case for successfully updating the status of a menu item.
     */
    @Test
    public void testUpdateMenuItemStatusSuccess() throws Exception {
        CommonResponseDto responseDto = new CommonResponseDto();
        responseDto.setMessage("Menu item status updated successfully");

        when(menuItemService.updateMenuItemStatus(1)).thenReturn(responseDto);

        mockMvc.perform(put("/api/menuItems/menuItem/1/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Menu item status updated successfully"));
    }

    /**
     * Test case for successfully updating a menu item.
     */
    @Test
    public void testUpdateMenuItemSuccess() throws Exception {
        MenuItemUpdateRequestDto updateDto = new MenuItemUpdateRequestDto();
        updateDto.setName("Pasta");

        CommonResponseDto responseDto = new CommonResponseDto();
        responseDto.setMessage("Menu item updated successfully");

        MockMultipartFile multipartFile = new MockMultipartFile("image", "pasta.jpg", "image/jpeg", new byte[0]);

        when(menuItemService.updateMenuItem(eq(1), any(MenuItemUpdateRequestDto.class), any(MultipartFile.class)))
                .thenReturn(responseDto);

        MvcResult result = mockMvc.perform(multipart("/api/menuItems/update/1/menuItem")
                        .file(multipartFile)
                        .param("foodName", "Pasta")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("Response Content: " + content);

        mockMvc.perform(multipart("/api/menuItems/update/1/menuItem")
                        .file(multipartFile)
                        .param("foodName", "Pasta")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Menu item updated successfully"));
    }
}
