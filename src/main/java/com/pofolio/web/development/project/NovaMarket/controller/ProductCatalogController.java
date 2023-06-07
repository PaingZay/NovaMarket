package com.pofolio.web.development.project.NovaMarket.controller;

import com.pofolio.web.development.project.NovaMarket.entity.Category;
import com.pofolio.web.development.project.NovaMarket.entity.OrderItem;
import com.pofolio.web.development.project.NovaMarket.entity.Product;
import com.pofolio.web.development.project.NovaMarket.entity.Wishlist;
import com.pofolio.web.development.project.NovaMarket.service.CategoryService;
import com.pofolio.web.development.project.NovaMarket.service.ProductService;
import com.pofolio.web.development.project.NovaMarket.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductCatalogController {

    @Autowired
    ProductService productService;

    @Autowired
    WishlistService wishlistService;

    @Autowired
    CategoryService categoryService;

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

    //Just Testing Getting Products by Name
    @GetMapping("/products/{productInfo}")
    public ResponseEntity<Page<Product>> getProductsByName(@RequestParam int pageSize, @RequestParam int pageNumber, @PathVariable("productInfo") String productInfo) {
//        logger.info("Getting all members");

        try {
            Page<Product> products = productService.searchProduct(productInfo,pageNumber,pageSize);
            if (products.isEmpty()) {

                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
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

    @GetMapping("/products/category/{categoryName}")
    public ResponseEntity<Page<Product>> searchByCategory(@RequestParam int pageSize, @RequestParam int pageNumber, @PathVariable("categoryName") String categoryName) {
//        logger.info("Getting all members");

        try {
            Category dbCategory= categoryService.getCategoriesByName(categoryName).get();

            Page<Product> products = productService.searchByCategory(pageSize,pageNumber,dbCategory.getId());
//            System.out.println(pageNumber+ " " +pageSize);
            if (products.isEmpty()) {
                System.out.println("Cannot find the items with" + categoryName + " category");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
