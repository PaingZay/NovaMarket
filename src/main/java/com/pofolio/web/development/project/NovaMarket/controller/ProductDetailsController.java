package com.pofolio.web.development.project.NovaMarket.controller;

import com.pofolio.web.development.project.NovaMarket.entity.Customer;
import com.pofolio.web.development.project.NovaMarket.entity.Product;
import com.pofolio.web.development.project.NovaMarket.entity.Wishlist;
import com.pofolio.web.development.project.NovaMarket.entity.WishlistItem;
import com.pofolio.web.development.project.NovaMarket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("wishlists/{productId}")
    public ResponseEntity<Wishlist> addToWishlist(@PathVariable(value = "productId") Long productId, @RequestBody Wishlist wishlist) {
        try {

        //CustomerId from Session!!!!!!!!!
        Long customerId = 1L;

        Customer savedCustomer = userService.registerUser(userService.findUserById(customerId).get());

        //Wishlist wishlist = new Wishlist();
        wishlist.setCustomer(savedCustomer);
        wishlist.setCreatedDate(LocalDate.now());

        Wishlist savedWishlist = wishlistService.addToWishlist(wishlist);

        System.out.println("Saved Wish List" + savedWishlist.toString());

        WishlistItem wishlistItem = new WishlistItem();

        wishlistItem.setWishlist(savedWishlist);

        //ERROR//wishlistItem.setProduct(productService.getProductById(productId).get());

        WishlistItem wl = wishlistItemService.createWishlistItem(wishlistItem);

        System.out.println("Saved Wishlist Item"+ wl.toString());

        return new ResponseEntity<>(savedWishlist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/wishlists/{id}")
    public ResponseEntity<Wishlist> getMemberById(@PathVariable("id") Long id){

        return wishlistService.findWishlistById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
