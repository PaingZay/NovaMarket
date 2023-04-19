package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Customer;

import java.util.Optional;

public interface UserService {
    Customer authenticate(String email, String password);

    Customer registerUser(Customer customer);

    Boolean findUserByEmail(String email);

    Optional<Customer> findUserById(Long id);
}
