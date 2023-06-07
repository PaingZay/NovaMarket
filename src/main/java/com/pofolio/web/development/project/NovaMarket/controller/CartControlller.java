package com.pofolio.web.development.project.NovaMarket.controller;

import com.pofolio.web.development.project.NovaMarket.NovaMarketApplication;
import com.pofolio.web.development.project.NovaMarket.entity.Cart;
import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import com.pofolio.web.development.project.NovaMarket.service.CartItemService;
import com.pofolio.web.development.project.NovaMarket.service.CartService;
import com.pofolio.web.development.project.NovaMarket.service.UserService;
import com.pofolio.web.development.project.NovaMarket.utils.ExtractJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CartControlller {

    @Autowired
    CartService cartService;
    private static final Logger logger = LoggerFactory.getLogger(NovaMarketApplication.class);

    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;

    @GetMapping("/cart/{customerId}")
    public ResponseEntity<Cart> getCartById(@PathVariable("customerId") Long customerId){
        logger.info("Get cart, id" + customerId);
        try {
            Cart savedCart = cartService.getSpecificCart(customerId).get();

            if (savedCart != null) {
                return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);

            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //JWT TEST
    @GetMapping("/cart/secure/{customerId}")
    //@RequestHeader.... passing the access token to our backend application
    //It's validating with Okta automatically
    public ResponseEntity<Cart> getCartByIdJWT(@RequestHeader(value = "Authorization") String token, @PathVariable("customerId") Long customerId){
        logger.info("Get cart, id" + customerId);
        try {
            String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

            //I think customerId is not needed in this situation
            Long customerId2 = userService.findUserByEmail(userEmail).get().getId();

            Cart savedCart = cartService.getSpecificCart(customerId2).get();

            if (savedCart != null) {
                return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get the specific cart of a customer
    //If there is no existing cart then
    //Redirect to following post method

    @PostMapping("/cart/secure")
    public ResponseEntity<Cart> saveMember(@RequestHeader(value = "Authorization") String token, @RequestBody Cart cart) {
        logger.info("Creating new member");
        try {
            Cart createdCart = cartService.saveCart(cart);

            return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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


    @GetMapping("/cart/items/secure/{cartId}")
    public ResponseEntity<List<CartItem>> secureGetAllProducts(@RequestHeader(value = "Authorization", required=false) String token, @PathVariable("cartId") Long cartId) {
//        logger.info("Getting all members");
        try {

            List<CartItem> cartItems = cartItemService.getCartItemListByCartId(cartId);
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
    @PutMapping("/cart/items/secure/addToCart")
    public ResponseEntity<CartItem> AddToCart(@RequestHeader(value = "Authorization") String token,@RequestBody CartItem cartItem) {
        logger.info("Update new member");
        try {

            String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

            Long customerId = userService.findUserByEmail(userEmail).get().getId();

            CartItem updatedCartItem = cartItemService.addCartItem(cartItem);

            return new ResponseEntity<>(updatedCartItem, HttpStatus.CREATED);
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

    //When click checkout call this method

    @PutMapping("/cart")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        logger.info("Update new member");

        try {
            Cart savedCart = cartService.updateCart(cart, cart.getId());

            System.out.println( "Updated Cart" + savedCart);

            return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cart/items/secure/increase/{productId}")
    public ResponseEntity<CartItem> increaseQuantity(@RequestHeader(value = "Authorization") String token, @PathVariable("productId") Long productId) {
        logger.info("Update new member");
        try {

            String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

            Long customerId = userService.findUserByEmail(userEmail).get().getId();

            CartItem updatedCartItem = cartItemService.increaseQuantity(customerId, productId);

            System.out.println( "Updated Cart" + updatedCartItem);

            return new ResponseEntity<>(updatedCartItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cart/items/secure/decrease/{productId}")
    public ResponseEntity<CartItem> decreaseQuantity(@RequestHeader(value = "Authorization") String token, @PathVariable("productId") Long productId) {
        logger.info("Update new member");
        try {

            String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

            Long customerId = userService.findUserByEmail(userEmail).get().getId();

            CartItem updatedCartItem = cartItemService.decreaseQuantity(customerId, productId);

            return new ResponseEntity<>(updatedCartItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PutMapping("/cart/items/secure/total")
//    public ResponseEntity<Double> calculateSubtotal(@RequestHeader(value = "Authorization") String token) {
//        logger.info("Update new member");
//        try {
//
//            String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
//
//            Long customerId = userService.findUserByEmail(userEmail).get().getId();
//
//            double total = cartItemService.calculateSubtotal(customerId);
//
//            return new ResponseEntity<>(total, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //Testing Purpose & Not Necessarily Needed

    @GetMapping("/AllCarts")
    public ResponseEntity<Page<Cart>> getAllProducts(@RequestParam int pageSize, @RequestParam int pageNumber) {
//        logger.info("Getting all members");
        try {
            Page<Cart> carts = cartService.getCartList(pageSize,pageNumber);
            if (carts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(carts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
