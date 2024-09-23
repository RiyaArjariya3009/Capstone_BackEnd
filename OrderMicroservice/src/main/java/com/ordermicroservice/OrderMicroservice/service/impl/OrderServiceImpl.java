package com.ordermicroservice.OrderMicroservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ordermicroservice.OrderMicroservice.DtoConversion.DtoConversion;
import com.ordermicroservice.OrderMicroservice.constants.Constants;
import com.ordermicroservice.OrderMicroservice.dtos.CommonResponseDto;
import com.ordermicroservice.OrderMicroservice.dtos.OrderRequestDto;
import com.ordermicroservice.OrderMicroservice.dtos.OrderResponseDto;
import com.ordermicroservice.OrderMicroservice.dtos.UserResponseDto;
import com.ordermicroservice.OrderMicroservice.entities.Cart;
import com.ordermicroservice.OrderMicroservice.entities.Order;
import com.ordermicroservice.OrderMicroservice.enums.OrderStatus;
import com.ordermicroservice.OrderMicroservice.enums.RoleType;
import com.ordermicroservice.OrderMicroservice.exceptions.CartItemNotFoundException;
import com.ordermicroservice.OrderMicroservice.exceptions.OrderNotFoundException;
import com.ordermicroservice.OrderMicroservice.exceptions.OrderUpdateException;
import com.ordermicroservice.OrderMicroservice.exceptions.UnauthorizedActionException;
import com.ordermicroservice.OrderMicroservice.feignclient.AddressFeignClient;
import com.ordermicroservice.OrderMicroservice.feignclient.UserFeignClient;
import com.ordermicroservice.OrderMicroservice.repositories.OrderRepository;
import com.ordermicroservice.OrderMicroservice.service.CartService;
import com.ordermicroservice.OrderMicroservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserFeignClient userClient;

    @Autowired
    private AddressFeignClient addressClient;

    @Override
    public CommonResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        UserResponseDto user = userClient.getUserById(orderRequestDto.getUserId());
        if(user == null || !user.getRole().equals("CUSTOMER")) {
            throw new UnauthorizedActionException(Constants.CUSTOMER_NOT_FOUND);
        }


        List<Cart> cartItems = cartService.getCartItemsByUserIdAndRestaurantId(orderRequestDto.getUserId(), orderRequestDto.getRestaurantId());

        if (cartItems.isEmpty()) {
            throw new CartItemNotFoundException("No items found in the cart for user " + orderRequestDto.getUserId() + " and restaurant " + orderRequestDto.getRestaurantId());
        }

        List<Cart> availableCartItems = new ArrayList<>();
        List<Cart> unavailableCartItems = new ArrayList<>();

        for (Cart cartItemDto : orderRequestDto.getCartItems()) {
            boolean itemExists = cartItems.stream()
                    .anyMatch(cartItem -> cartItem.getMenuItemId() == cartItemDto.getMenuItemId() // Compare primitives with '=='
                            && cartItem.getQuantity() == cartItemDto.getQuantity() // Compare primitives with '=='
                    ); // Use compareTo() for BigDecimal
         // && cartItem.getPrice().compareTo(cartItemDto.getPrice()) == 0)

            if (itemExists) {
                Cart cart = new Cart();
                cart.setUserId(cartItemDto.getUserId());
                cart.setMenuItemId(cartItemDto.getMenuItemId());
                cart.setQuantity(cartItemDto.getQuantity());
                cart.setPrice(cartItemDto.getPrice());
                cart.setRestaurantId(cartItemDto.getRestaurantId());

                availableCartItems.add(cart);
            } else {
                unavailableCartItems.add(cartItemDto);
            }
        }

        if (!unavailableCartItems.isEmpty()) {
            return new CommonResponseDto("Some items are not valid in the cart: " +  unavailableCartItems.stream()
                    .map(dto -> String.format("MenutemId=%d, quantity=%d, price=%s", dto.getMenuItemId(), dto.getQuantity(), dto.getPrice()))
                    .collect(Collectors.joining(", ")));
        }

        BigDecimal totalPrice = availableCartItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        double negatedValue = totalPrice.doubleValue();
        userClient.updateWalletBalance(orderRequestDto.getUserId(), negatedValue);

        Order order = DtoConversion.convertOrderInDtoToOrder(orderRequestDto);
        order.setRestaurantId(orderRequestDto.getRestaurantId());
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setOrderTime(LocalDateTime.now());
        try {
            order.setCartItemsFromList(availableCartItems );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to process cart items", e);
        }

        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);

        cartService.clearCartAfterOrderPlaced(orderRequestDto.getUserId(), orderRequestDto.getRestaurantId());

        return new CommonResponseDto(Constants.ORDER_PLACED_SUCCESSFULLY);}

        @Override
        public CommonResponseDto cancelOrder(int orderId) {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException("Order not found for ID: " + orderId));

            if (order.getOrderTime().plusSeconds(1000).isBefore(LocalDateTime.now())) {
                throw new OrderUpdateException("Order cannot be cancelled after 30 seconds.");
            }

           // userClient.updateWalletBalance(order.getUserId(), order.getTotalPrice());

            order.setOrderStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
            return new CommonResponseDto(Constants.ORDER_CANCELLED_SUCCESSFULLY);
        }
    @Override
    public List<OrderResponseDto> getOrdersByUserId(int userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        List<OrderResponseDto> orderOutDtoList=new ArrayList<>();
        for(Order order:orders){
            orderOutDtoList.add(DtoConversion.convertOrderToOrderOutDto(order));
        }
        return orderOutDtoList;
    }

    @Override
    public List<OrderResponseDto> getOrdersByRestaurantId(int restaurantId) {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
        List<OrderResponseDto> orderOutDtoList=new ArrayList<>();
        for(Order order:orders){
            orderOutDtoList.add(DtoConversion.convertOrderToOrderOutDto(order));
        }
        return orderOutDtoList;
    }

    @Override
    public void markOrderAsCompleted(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found for ID: " + orderId));

        order.setOrderStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
        CommonResponseDto message= new CommonResponseDto(Constants.ORDER_COMPLETED_SUCCESSFULLY);
    }

}




