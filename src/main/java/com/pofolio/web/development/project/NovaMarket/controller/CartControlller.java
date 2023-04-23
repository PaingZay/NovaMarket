package com.pofolio.web.development.project.NovaMarket.controller;

import com.pofolio.web.development.project.NovaMarket.NovaMarketApplication;
import com.pofolio.web.development.project.NovaMarket.entity.Cart;
import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import com.pofolio.web.development.project.NovaMarket.service.CartItemService;
import com.pofolio.web.development.project.NovaMarket.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartControlller {

    @Autowired
    CartService cartService;
    private static final Logger logger = LoggerFactory.getLogger(NovaMarketApplication.class);

    @Autowired
    CartItemService cartItemService;

    @GetMapping("/cart/{customerId}")
    public ResponseEntity<Cart> getCartById(@PathVariable("customerId") Long customerId){
        logger.info("Get cart, id" + customerId);

        Cart savedCart = cartService.getSpecificCart(customerId).get();

        System.out.println("IN CONTROLLER" + savedCart);

        if(savedCart != null) {
            return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
        }
        else {
            Cart unsavedCart = new Cart();
            return new ResponseEntity<>(unsavedCart,HttpStatus.CONFLICT);
        }
    }

    //Client check existing active cart
    //If existed call following method
    //Then add new cart_items with the existing cartId and his/her customerId using saveAndFlush

    @GetMapping("/cart/items/{cartId}")
    public ResponseEntity<Page<CartItem>> getAllProducts(@RequestParam int pageSize, @RequestParam int pageNumber, @PathVariable("cartId") Long cartId) {
//        logger.info("Getting all members");
        try {
            Page<CartItem> cartItems = cartItemService.getAllCartItems(pageSize,pageNumber,cartId);
            if (cartItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //If there is nothing or inactive cart just create new cart obj
    //Then add new cart_items with that saved cartId using saveAndFlush

    //POSTMapping because we are adding new cart item but not updating cart
    //BTW this is AddToCart Method
    @PostMapping("/cart/items")
    public ResponseEntity<CartItem> addItemsToCart(@RequestBody CartItem cartItem) {
        logger.info("Adding items to cart");
        try {
            CartItem savedCartItem = cartItemService.addCartItem(cartItem);
            System.out.println(savedCartItem);
                return new ResponseEntity<>(savedCartItem, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //This is RemoveFromCart Method

    @DeleteMapping("/cart/items/{cartItemId}")
    public ResponseEntity<Long> deleteCartItem(@PathVariable("cartItemId") Long cartItemId) {
        logger.info("Deleting existing member");
        try {
            var isRemoved = cartItemService.deleteCartItemById(cartItemId);

            if (!isRemoved) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(cartItemId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cart/allitems/{cartId}")
    public ResponseEntity<Long> removeAllHistory(@PathVariable Long cartId ) {
        try {
            var isRemoved = cartItemService.removeAllCartItemsById(cartId);

            if (!isRemoved) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
