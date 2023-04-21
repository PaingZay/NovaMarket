package com.pofolio.web.development.project.NovaMarket.controller;

import com.pofolio.web.development.project.NovaMarket.entity.Customer;
import com.pofolio.web.development.project.NovaMarket.entity.Product;
import com.pofolio.web.development.project.NovaMarket.entity.Wishlist;
import com.pofolio.web.development.project.NovaMarket.entity.WishlistItem;
import com.pofolio.web.development.project.NovaMarket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductDetailsController {

    @Autowired
    ProductService productService;

    @Autowired
    WishlistService wishlistService;

    @Autowired
    WishlistItemService wishlistItemService;
    @Autowired
    UserService userService;

//    @Operation(summary = "Get a product", description = "Retrieve an existing product details based on unique key ID")
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findProductDetailsById(@PathVariable(value = "id") Long id) {
        return productService.findProductDetailsById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //METHOD
    @PostMapping("/wishlists/{productId}")
    public ResponseEntity<Wishlist> addToWishlist(@PathVariable(value = "productId") Long productId, @RequestBody Wishlist wishlist) {
        try {

        //CustomerId from Session!!!!!!!!!
        Long customerId = 1L;

        //Customer savedCustomer = userService.registerUser(userService.findUserById(customerId).get());

        //shanmon updated code
        Optional<Customer> savedCustomerOpt = userService.findUserById(customerId);
        Customer savedCustomer = new Customer();
        if(savedCustomerOpt.isPresent()){
            savedCustomer = savedCustomerOpt.get();
            System.out.println("Saved Customer" + savedCustomer.toString());
        }

        Wishlist wishlists = new Wishlist();
        wishlists.setCustomer(savedCustomer);
        wishlists.setCreatedDate(LocalDate.now());
        Wishlist savedWishlist = wishlistService.addToWishlist(wishlist);

        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setWishlist(savedWishlist);

        if(productService.getProductById(productId).isPresent()) {
                wishlistItem.setProduct(productService.getProductById(productId).get());
        }

        WishlistItem wl = wishlistItemService.createWishlistItem(wishlistItem);

        return new ResponseEntity<>(savedWishlist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/wishlists/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable("id") Long id){

        return wishlistService.findWishlistById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @GetMapping("/wishlists")
//    public ResponseEntity<List<Wishlist>> getAllWishlists() {
//
//        try {
//            List<Wishlist> wishlists = wishlistService.getAllWishlists();
//
//            if (wishlists.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(wishlists, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/wishlists")
    public ResponseEntity<Page<Wishlist>> getAllWishlists(@RequestParam int pageSize, @RequestParam int pageNumber) {
//        logger.info("Getting all members");

        try {
            Page<Wishlist> wishlists = wishlistService.getAllWishlists(pageSize,pageNumber);

            if (wishlists.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            for (Wishlist wl: wishlists)
            {
                System.out.println("Hello"+wl);
            }

            return new ResponseEntity<>(wishlists, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
