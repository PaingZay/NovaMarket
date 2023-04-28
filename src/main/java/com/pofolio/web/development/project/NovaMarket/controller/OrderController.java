package com.pofolio.web.development.project.NovaMarket.controller;

import com.pofolio.web.development.project.NovaMarket.NovaMarketApplication;
import com.pofolio.web.development.project.NovaMarket.entity.Cart;
import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import com.pofolio.web.development.project.NovaMarket.entity.Order;
import com.pofolio.web.development.project.NovaMarket.entity.OrderItem;
import com.pofolio.web.development.project.NovaMarket.service.OrderItemService;
import com.pofolio.web.development.project.NovaMarket.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    private static final Logger logger = LoggerFactory.getLogger(NovaMarketApplication.class);

    //Get order object first using customerId
    @GetMapping("/order/{customerId}")
    public ResponseEntity<Order> getCartById(@PathVariable("customerId") Long customerId){
        logger.info("Get cart, id" + customerId);

        System.out.println("Hello" + customerId);

        Order savedOrder = orderService.getSpecificOrder(customerId).get();

        System.out.println("IN CONTROLLER" + savedOrder);

        if(savedOrder != null) {
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        }
        else {
            Order unsavedOrder = new Order();
            return new ResponseEntity<>(unsavedOrder,HttpStatus.CONFLICT);
        }
    }

    //orderId: Auto generated
    //customer: Get customer object from session and set it in order object on client side
    //orderDate: Auto generated
    //totalAmount: 0.0;
    //status: "Active"

    @PostMapping("/order")
    public ResponseEntity<Order> saveMember(@RequestBody Order order) {
        logger.info("Creating new order");
        try {
            Order savedOrder = orderService.saveOrder(order);
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Then find order items using orderId
    @GetMapping("/order/items/{orderId}")
    public ResponseEntity<Page<OrderItem>> getAllOrderItems(@RequestParam int pageSize, @RequestParam int pageNumber, @PathVariable("orderId") Long orderId) {
//        logger.info("Getting all members");
        try {
            Page<OrderItem> orderItems = orderItemService.getAllOrderItems(pageSize,pageNumber, orderId);
            if (orderItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Make sure the checkout button is disable when there is no items in the cart.
    //orderId must be set and call this following method to save order item on Client Side

    @PostMapping("/order/items/{cartItemId}")
    public ResponseEntity<List<OrderItem>> addItemsToCart(@RequestBody OrderItem orderItem, @PathVariable("cartItemId") Long cartItemId) {
        logger.info("Creating order items");
        try {

            List<OrderItem> savedOrderItems = orderItemService.copyRows(orderItem, cartItemId);
            System.out.println(savedOrderItems);

            return new ResponseEntity<>(savedOrderItems, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
