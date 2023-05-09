package com.pofolio.web.development.project.NovaMarket.controller;

import com.pofolio.web.development.project.NovaMarket.NovaMarketApplication;
import com.pofolio.web.development.project.NovaMarket.entity.Customer;
import com.pofolio.web.development.project.NovaMarket.entity.Review;
import com.pofolio.web.development.project.NovaMarket.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(NovaMarketApplication.class);

    @PostMapping("/members")
    public ResponseEntity<Customer> saveMember(@RequestBody Customer customer) {
        logger.info("Creating new member");
        try {
            System.out.println("Hello1");
            Boolean existingStatus = userService.findUserByEmail(customer.getEmail());
            if (!existingStatus) {
                System.out.println("Hello2");
                Customer savedUser = userService.registerUser(customer);
                return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
            }
            else {
                Customer unsavedUser = new Customer();
                return new ResponseEntity<>(unsavedUser,HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/customers")
//    public ResponseEntity<Page<Customer>> getAllReviewsByProductId(@RequestParam int pageSize, @RequestParam int pageNumber) {
//        try {
//            Page<Customer> customers = userService.getAllCustomers(pageSize,pageNumber);
//            if (customers.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(customers, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/users")
    public ResponseEntity<List<Customer>> getAllReviewsByProductId() {
        try {
            List<Customer> customers = userService.getAllCustomers();
            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/users")
//    public List<Customer> getAllReviewsByProductId() {
//        try {
//            List<Customer> customers = userService.getAllCustomers();
//            return customers;
//
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
