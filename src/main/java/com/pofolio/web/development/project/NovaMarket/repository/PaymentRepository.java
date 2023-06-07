package com.pofolio.web.development.project.NovaMarket.repository;

import com.pofolio.web.development.project.NovaMarket.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Optional<Payment> findByUserId(Long customerId);
}
