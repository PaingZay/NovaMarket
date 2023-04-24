package com.pofolio.web.development.project.NovaMarket.controller;

import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import com.pofolio.web.development.project.NovaMarket.entity.OrderItem;
import com.pofolio.web.development.project.NovaMarket.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/order/items/{orderId}")
    public ResponseEntity<Page<OrderItem>> getAllProducts(@RequestParam int pageSize, @RequestParam int pageNumber, @PathVariable("orderId") Long orderId) {
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
}
