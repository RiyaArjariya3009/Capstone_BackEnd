package com.ordermicroservice.OrderMicroservice.controller;
import com.ordermicroservice.OrderMicroservice.dtos.CartItemRequestDto;
import com.ordermicroservice.OrderMicroservice.dtos.CartItemResponseDto;
import com.ordermicroservice.OrderMicroservice.dtos.CommonResponseDto;
import com.ordermicroservice.OrderMicroservice.entities.Cart;
import com.ordermicroservice.OrderMicroservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CommonResponseDto> addItemToCart(@RequestBody CartItemRequestDto cartItemRequestDto) {
        CommonResponseDto response = cartService.addItemToCart(cartItemRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/update/{cartId}")
    public ResponseEntity<CommonResponseDto> updateItemQuantity(  @PathVariable int cartId, @RequestParam int quantityChange) {
        CommonResponseDto response = cartService.updateCartItemQuantity(cartId, quantityChange);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/removeItem/byCardId/{cartId}")
    public ResponseEntity<CommonResponseDto> removeItemFromCart(@PathVariable int cartId) {
        CommonResponseDto response = cartService.removeItemFromCart(cartId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/clear/user/{userId}/restaurant/{restaurantId}")
    public ResponseEntity<CommonResponseDto> clearCartAfterOrderPlaced(@PathVariable int userId,@PathVariable int restaurantId) {
        CommonResponseDto response = cartService.clearCartAfterOrderPlaced(userId,restaurantId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/getCartByUserId/{userId}")
    public ResponseEntity<List<Cart>> getCartByUserId(@PathVariable int userId)
    {
        List<Cart> cartItems = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartItems,HttpStatus.OK);
    }
    @GetMapping("getCartById/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable int cartId) {
        Cart cart = cartService.getCartById(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    @GetMapping("getCartBy/user/{userId}/restaurant/{restaurantId}")
    public ResponseEntity<List<Cart>> getCartByUserIdAndRestaurantId(@PathVariable int userId, @PathVariable int restaurantId) {
        List<Cart> cartItems = cartService.getCartItemsByUserIdAndRestaurantId(userId,restaurantId);
        return new ResponseEntity<>(cartItems,HttpStatus.OK);
    }

}

