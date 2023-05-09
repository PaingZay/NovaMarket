package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Customer authenticate(String email, String password);

    Customer registerUser(Customer customer);

    Boolean findUserByEmail(String email);

    Optional<Customer> findUserById(Long id);

    List<Customer> getAllCustomers();

//    List<Customer> getAllCustomers();
}
