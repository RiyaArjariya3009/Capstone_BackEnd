package com.restaurant.userservice.controller;

import com.restaurant.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    public void testRegisterUserSuccess() throws Exception {
        // Perform the POST request to register a user
        String jsonResponse = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"riya\",\"password\":\"password123\",\"email\":\"rii@example.com\",\"address\":{\"street\":\"123 Main St\",\"city\":\"Springfield\",\"state\":\"IL\",\"zipCode\":\"62701\",\"country\":\"USA\"},\"role\":\"USER\"}"))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        // Define the expected JSON response
        String expectedJson = "{\"username\":\"riya\",\"email\":\"rii@example.com\",\"role\":\"USER\",\"addressId\":1}";

        // Assert that the actual JSON response matches the expected JSON, ignoring any additional fields
      //  assertEquals(expectedJson, jsonResponse, false);
        JSONAssert.assertEquals(expectedJson, jsonResponse, false);
    }

}
    /*@Test
    void testRegisterUserSuccess() throws Exception {
        Address address = new Address("123 Main St", "Springfield", "IL", "62701", "USA");
        UserRegistrationDto userDto = new UserRegistrationDto("riya", "password123", "rii@example.com", address, RoleType.USER);
        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto("riya", "rii@example.com", "USER");

        when(userService.registerUser(userDto)).thenReturn(responseDto);

        String requestJson = "{\"username\":\"riya\",\"password\":\"password123\",\"email\":\"rii@example.com\",\"address\":{\"street\":\"123 Main St\",\"city\":\"Springfield\",\"state\":\"IL\",\"zipCode\":\"62701\",\"country\":\"USA\"},\"role\":\"USER\"}";
        String expectedJson = "{\"username\":\"riya\",\"email\":\"rii@example.com\",\"role\":\"USER\"}";

        MvcResult result = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();

        String actualJson = result.getResponse().getContentAsString();
        System.out.println("Actual Response JSON: " + actualJson);

        JSONAssert.assertEquals(expectedJson, actualJson, false); // false for strict mode
    }*/

    /*@Test
    void testRegisterUserSuccess() throws Exception {
        Address address = new Address("123 Main St", "Springfield", "IL", "62701", "USA");
        UserRegistrationDto userDto = new UserRegistrationDto("riya", "password123", "rii@example.com", address, RoleType.USER);
        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto("riya","password123", "rii@example.com",1,  RoleType.USER);

        when(userService.registerUser(userDto)).thenReturn(responseDto);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"riya\",\"password\":\"password123\",\"email\":\"rii@example.com\",\"address\":{\"street\":\"123 Main St\",\"city\":\"Springfield\",\"state\":\"IL\",\"zipCode\":\"62701\",\"country\":\"USA\"},\"role\":\"USER\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"username\":\"riya\",\"email\":\"rii@example.com\",\"role\":\"USER\"}"));
    }*/

  /*  @Test
    void testRegisterUserSuccess() throws Exception {
        UserRegistrationDto userDto = new UserRegistrationDto("riya", "password123", "rii@example.com", "USER");
        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto("riya", "rii@example.com", "USER");

        when(userService.registerUser(userDto)).thenReturn(responseDto);

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"riya\",\"email\":\"rii@example.com\",\"role\":\"USER\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"username\":\"riya\",\"email\":\"rii@example.com\",\"role\":\"USER\"}"));
    }*/



    // Additional tests for registerUser, like validation failures

    /*@Test
    public void testRegisterUserSuccess() throws Exception {
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setUsername("testuser");
        userDto.setPassword("password123");
        userDto.setEmail("test@example.com");
        userDto.setAddress(new Address("123 Street", "City", "State", "12345", "Country"));
        userDto.setRole(RoleType.USER);

        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto();
        responseDto.setUsername(userDto.getUsername());
        responseDto.setEmail(userDto.getEmail());
        responseDto.setPassword(userDto.getPassword());
        responseDto.setAddressId(1); // Assuming address ID is generated
        responseDto.setRole(userDto.getRole());

        when(userService.registerUser(userDto)).thenReturn(responseDto);

       /* mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(responseDto)));*/
        /*MvcResult result = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andReturn();

        String expectedJson = new ObjectMapper().writeValueAsString(responseDto);
        String actualJson = result.getResponse().getContentAsString();

        JSONAssert.assertEquals(expectedJson, actualJson, true); // Use true for str
    }*/
