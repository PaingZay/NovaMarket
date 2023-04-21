package com.pofolio.web.development.project.NovaMarket.controller;

import com.pofolio.web.development.project.NovaMarket.entity.Product;
import com.pofolio.web.development.project.NovaMarket.entity.Wishlist;
import com.pofolio.web.development.project.NovaMarket.service.ProductService;
import com.pofolio.web.development.project.NovaMarket.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductCatalogController {

    @Autowired
    ProductService productService;

    @Autowired
    WishlistService wishlistService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam int pageSize, @RequestParam int pageNumber) {
//        logger.info("Getting all members");

        try {
            Page<Product> products = productService.getProductList(pageSize,pageNumber);
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/price_range")
    public ResponseEntity<Page<Product>> filterByPriceRange(@RequestParam int pageSize, @RequestParam int pageNumber,@RequestParam double startPrice, @RequestParam double endPrice) {
//        logger.info("Getting all members");

        try {
            Page<Product> products = productService.filterByPriceRange(pageSize,pageNumber,startPrice,endPrice);
//            System.out.println(pageNumber+ " " +pageSize);
            if (products.isEmpty()) {
                System.out.println("The List is Empty");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
