package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Customer;
import com.pofolio.web.development.project.NovaMarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public Customer authenticate(String email, String password) {
        return userRepository.findUserByNamePwd(email, password);
    }

    @Override
    public Customer registerUser(Customer customer) {
        return userRepository.save(customer);
    }

    @Override
    public Optional<Customer> findUserByEmail(String email) {
        Optional<Customer> user = userRepository.findUserByEmail(email);
        return user;
    }

    @Override
    public Boolean verifyExistingUserByEmail(String email) {
        Optional<Customer> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Optional<Customer> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return userRepository.findAll();
    }

//    @Override
//    public List<Customer> getAllCustomers(int pageSize, int pageNumber) {
//        return userRepository.findAll();
//    }
}
