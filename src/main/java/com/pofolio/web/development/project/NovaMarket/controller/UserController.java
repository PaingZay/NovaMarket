package com.pofolio.web.development.project.NovaMarket.controller;

import com.pofolio.web.development.project.NovaMarket.NovaMarketApplication;
import com.pofolio.web.development.project.NovaMarket.entity.Customer;
import com.pofolio.web.development.project.NovaMarket.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

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
}
