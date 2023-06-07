package com.pofolio.web.development.project.NovaMarket.controller;

import com.pofolio.web.development.project.NovaMarket.entity.Customer;
import com.pofolio.web.development.project.NovaMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CommonController {

    @Autowired
    UserService userService;

//    Returning Login Thymeleaf view
    @RequestMapping(value = {"/", "/login", "/home"}, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new Customer());

        System.out.println("This is login page DIO");

        return "login";
    }

    @RequestMapping(value = "/home/authenticate")//get user object containing username and password form view
    public String authenticate(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult, Model model, HttpSession session)
    {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        Customer dbCustomer = userService.authenticate(customer.getEmail(), customer.getPassword());
        if (dbCustomer == null) {
            model.addAttribute("loginMessage", "Incorrect username/password");
            return "login";
        }

        session.setAttribute("currentUserId",dbCustomer.getId());

        return "redirect:/staff/course/history";//NEED TO MODIFY DIRECTION
    }
}
