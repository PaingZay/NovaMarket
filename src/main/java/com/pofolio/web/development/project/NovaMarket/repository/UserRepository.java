package com.pofolio.web.development.project.NovaMarket.repository;

import com.pofolio.web.development.project.NovaMarket.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c FROM Customer c WHERE c.email=:email AND c.password=:password")
    Customer findUserByNamePwd(@Param("email")String email, @Param("password")String password);

    Optional<Customer> findUserByEmail(String email);
}
